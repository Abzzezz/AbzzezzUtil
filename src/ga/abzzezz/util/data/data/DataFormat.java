/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;

import ga.abzzezz.util.data.ClassUtil;
import ga.abzzezz.util.data.FileUtil;
import ga.abzzezz.util.data.URLUtil;
import ga.abzzezz.util.logging.Logger;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to format and decode data blocks
 * Takes in a file or url and converts it to an url
 */
public class DataFormat<V> {

    /**
     * Block Formatter instance
     */
    private static final BlockFormatter blockFormatter = new BlockFormatter();
    /**
     * URL to read from
     */
    private URL url;
    /**
     * List of all blocks to optimize decoding. Gets stored as soon as the constructor is called
     */
    private final HashMap<String, BlockData> allBlocks;

    /**
     * Init. Specify file, will be converted to URL
     *
     * @param file
     */
    public DataFormat(File file) {
        try {
            this.url = file.toURI().toURL();
        } catch (MalformedURLException e) {
            Logger.log("Error converting Filepath to URL. Check path", Logger.LogType.ERROR);
            e.printStackTrace();
        }
        this.allBlocks = getBlocks();
    }

    /**
     * Now possible to read from URLs too
     *
     * @param url
     */
    public DataFormat(URL url) {
        this.url = url;
        this.allBlocks = getBlocks();
    }

    /**
     * Format Data methods
     */


    /**
     * Data will be formatted to the "Abzzezz util data format" and stored to a file
     *
     * @param data
     * @return
     */


    public static void formatData(DataObject data, File file, boolean append, boolean newLine) {
        FileUtil.writeArrayListToFile(formatDataList(data), file, append, newLine);
    }

    /**
     * Format and add to List
     *
     * @param data
     * @return
     */
    public static List<String> formatDataList(DataObject data) {
        List<String> array = new ArrayList<>();
        for (Object o : data.getMap().keySet()) {
            String valMap = data.getMap().get(o).toString();
            String[] split = valMap.split(":");
            array.add(blockFormatter.formatBlock(o, split[0], split[1]));
        }
        return array;
    }

    /**
     * Append everything to a string, then return
     *
     * @param data
     * @return
     */
    public static String formatData(DataObject data) {
        StringBuilder stringBuilder = new StringBuilder();
        formatDataList(data).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    /**
     * Decode data and give value for specific key.
     *
     * @param keyIn
     * @return
     */
    public V decode(String keyIn) {
        try {
            BlockData blockData = getBlockDataFromKey(keyIn);
            V value = (V) blockData.getValue();
            DataType dataType = getDataType(blockData.getDataType());
            if (dataType == DataType.ARRAY || dataType == DataType.STRING || dataType == DataType.CHARACTER) {
                return value;
            } else {
                return (V) ClassUtil.getMethod(dataType.aClass, "valueOf", String.class).invoke(dataType.aClass, value);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Logger.log("Decoding file", Logger.LogType.ERROR);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decodes values to object array
     *
     * @param keyIn
     * @return
     */
    public V[] decodeToArray(String keyIn) {
        try {
            BlockData blockData = getBlockDataFromKey(keyIn);
            DataType dataType = getDataType(blockData.getDataType());
            if (dataType == DataType.ARRAY) {
                return regenerateArray((String) blockData.getValue(), blockData.getDataType());
            } else {
                Object value = (dataType == DataType.STRING ||
                        dataType == DataType.CHARACTER) ? blockData.getValue() : ClassUtil.getMethod(dataType.aClass, "valueOf", String.class).invoke(dataType.aClass, blockData.getValue());
                return (V[]) new Object[]{value};
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Logger.log("Decoding file", Logger.LogType.ERROR);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get value from block
     *
     * @param keyIn
     * @return
     * @getString
     */
    private BlockData getBlockDataFromKey(String keyIn) {
        try {
            return allBlocks.get(keyIn);
        } catch (NullPointerException e) {
            Logger.log("Error finding key", Logger.LogType.ERROR);
            return null;
        }
    }

    /**
     * Get all blocks from a list.
     * Block: {DataType:::key:::value}
     * Blocks can now be stored in one single line, instead of every block having its own line
     * Eg: {DataType:::key:::value} {DataType:::key:::value} {DataType:::key:::value}
     * Block 1                   Block2                   Block3
     *
     * @return
     */
    private HashMap<String, BlockData> getBlocks() {
        //New array to store blocks
        HashMap<String, BlockData> blockData = new HashMap<>();
        //For every line check for blocks
        URLUtil.getURLContentAsArray(url).stream().forEach(s -> {
            //Get total blocks
            int blockSize = blockFormatter.getBlockElements(s);
            //new stringbuilder for easier deletion
            StringBuilder builderLine = new StringBuilder(s);

            for (int i = 0; i < blockSize; i++) {
                Block block = new Block(builderLine.toString());
                //Put block key, data and value
                blockData.put(block.getKey(), new BlockData(block.getDataType(), block.getValue()));
                //Delete old block from builder so new once can be found
                builderLine.delete(block.getBlock()[0], block.getBlock()[1]);
            }
        });
        //Return block array
        return blockData;
    }

    /**
     * Formats intended array String back to array
     *
     * @param s
     * @return
     */

    private V[] regenerateArray(String s, String dataType1) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        DataType dataType = getDataType(dataType1.split(":")[1]);
        String[] values = s.substring(s.indexOf("[") + 1, s.indexOf("]")).split(",");
        Object[] array = new Object[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = ClassUtil.getMethod(dataType.aClass, "valueOf", String.class).invoke(dataType.aClass, values[i].replace(" ", ""));
        }
        return (V[]) array;
    }

    /**
     * Check if string "s" is equal to one of the available data types
     *
     * @param s
     * @return
     */
    private DataType getDataType(String s) {
        for (DataType value : DataType.values()) {
            if(s.contains("Array") && s.contains(value.getType())) return DataType.ARRAY;
            else if (s.contains(value.getType())) return value;
        }
        return null;
    }

    /**
     * Enum for DataTypes
     */
    private enum DataType {
        STRING(String.class),
        ARRAY(ArrayList.class),
        INTEGER(Integer.class),
        LONG(Long.class),
        DOUBLE(Double.class),
        FLOAT(Float.class),
        BYTE(Byte.class),
        SHORT(Short.class),
        CHARACTER(Character.class);
        Class aClass;
        DataType(Class c) {
            this.aClass = c;
        }
        public Class getaClass() {
            return aClass;
        }
        public String getType() {
            return aClass.getSimpleName();
        }
    }
}
