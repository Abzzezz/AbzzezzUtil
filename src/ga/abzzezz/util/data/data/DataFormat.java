/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;

import ga.abzzezz.util.array.ArrayUtil;
import ga.abzzezz.util.data.ClassUtil;
import ga.abzzezz.util.data.FileUtil;
import ga.abzzezz.util.data.URLUtil;
import ga.abzzezz.util.logging.Logger;
import ga.abzzezz.util.stringing.StringUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to format and decode data blocks
 * Takes in a file or url and converts it to an url
 */
public class DataFormat {

    /**
     * URL to read from
     */
    private URL url;
    /**
     * Should new lines be created?
     * used for storing to a file
     */
    private static boolean newLine;
    /**
     * List of all blocks to optimize decoding. Gets stored as soon as the constructor is called
     */
    private List<String> allBlocks;
    /**
     * Block Formatter instance
     */
    private static BlockFormatter blockFormatter = new BlockFormatter();

    /**
     * Init. Specify file, will be converted to URL
     *
     * @param file
     */
    public DataFormat(File file) {
        try {
            this.url = file.toURI().toURL();
        } catch (MalformedURLException e) {
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
    public static void formatData(DataObject data, File file, boolean append) {
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
        for (Object o : data.getMap().keySet()) {
            String valMap = data.getMap().get(o).toString();
            String[] split = valMap.split(":");
            stringBuilder.append(blockFormatter.formatBlock(o, split[0], split[1]));
        }
        return stringBuilder.toString();
    }


    /**
     * Decoding
     */

    /**
     * Decode data and give value for specific key.
     *
     * @param keyIn
     * @return
     */
    public Object decode(String keyIn) {
        try {
            String value = getValueFromKey(keyIn);
            DataType dataType = getDataType(getData(keyIn));
            if (dataType == DataType.ARRAY || dataType == DataType.STRING || dataType == DataType.CHARACTER) {
                return value;
            } else {
                return ClassUtil.getMethod(dataType.aClass, "valueOf", new Class[]{String.class}).invoke(dataType.aClass, value);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Logger.log("Decoding file", Logger.LogType.ERROR);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decodes values to object array
     * @param keyIn
     * @return
     */
    public Object[] decodeToArray(String keyIn) {
        Object[] array = new Object[1];
        try {
            String value = getValueFromKey(keyIn);
            DataType dataType = getDataType(getData(keyIn));
            if (dataType == DataType.ARRAY) {
                array = regenerateArray(value);
            } else {
                array[0] = (dataType == DataType.STRING ||
                        dataType == DataType.CHARACTER) ? array[0] = value : ClassUtil.getMethod(dataType.aClass, "valueOf", new Class[]{String.class}).invoke(dataType.aClass, value);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return array;
    }


    /**
     * Get value from block
     * @getString
     * @param keyIn
     * @return
     */
    private String getValueFromKey(String keyIn) {
        return getString(keyIn).split(StringUtil.splitter)[2];
    }

    /**
     * Get datatype from block
     * @param keyIn
     * @return
     */
    private String getData(String keyIn) {
        return getString(keyIn).split(StringUtil.splitter)[0];
    }

    /**
     * Get block with desired keyword
     *
     * @param keyIn
     * @return
     */
    private String getString(String keyIn) {
        return allBlocks.get(ArrayUtil.indexOfKey(allBlocks, keyIn));
    }

    /**
     * Get all blocks from a list.
     * Block: {DataType:::key:::value}
     * Blocks can now be stored in one single line, instead of every block having its own line
     * Eg: {DataType:::key:::value} {DataType:::key:::value} {DataType:::key:::value}
     * Block 1                  Block2                   Block3
     *
     * @return
     */
    private List<String> getBlocks() {
        /*
        Get all lines from url
         */
        List<String> lines = URLUtil.getURLContentAsArray(url);
        //New array to store blocks
        List<String> blocks = new ArrayList<>();
        //For every line check for blocks
        for (String line : lines) {
            //Get total blocks
            int blockSize = blockFormatter.getBlockElements(line);
            //new stringbuilder for easier deletion
            StringBuilder builderLine = new StringBuilder(line);
            for (int i = 0; i < blockSize; i++) {
                Block block = new Block(builderLine.toString());
                //Get block bounds
                int[] blockBounds = block.getBlock();
                //Add inner block to blocks array
                blocks.add(block.getInnerBlock());
                //Delete old block from builder so new once can be found
                builderLine.delete(blockBounds[0], blockBounds[1]);
            }
        }

        //Return block array
        return blocks;
    }


    /**
     * Formats intended array String back to array
     *
     * @param s
     * @return
     */

    private Object[] regenerateArray(String s) {
        String s1 = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
        String[] split = s1.split(",");
        Object[] array = new Object[split.length];
        for (int i = 0; i < split.length; i++) { array[i] = split[i]; }
        return array;
    }

    /**
     * Check if string "s" is equal to one of the available data types
     *
     * @param s
     * @return
     */
    private DataType getDataType(String s) {
        for (DataType value : DataType.values()) {
            if (s.equalsIgnoreCase(value.getType())) return value;
        }
        return null;
    }

    public static void setNewLine(boolean newLine1) {
        newLine = newLine1;
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

        /**
         * Will be used in the near future
         * @param dataType
         * @return
         */
        public boolean isDataChar(DataType dataType) {
            return dataType == DataType.STRING ||
                    dataType == DataType.CHARACTER;
        }

        public String getType() {
            return aClass.getSimpleName();
        }
    }
}
