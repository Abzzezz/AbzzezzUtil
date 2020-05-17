/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
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
import jdk.internal.vm.annotation.Hidden;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataFormat {

    private URL url;
    private boolean newLine;
    private List<String> allBlocks;

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
     * Data will be formatted to the "Abzzezz util data format" and stored to a file
     *
     * @param data
     * @return
     */
    public void formatData(DataObject data, File file, boolean append) {
        FileUtil.writeArrayListToFile(formatDataList(data), file, append, newLine);
    }

    /**
     * Format and add to List
     *
     * @param data
     * @return
     */
    public List<String> formatDataList(DataObject data) {
        List<String> array = new ArrayList<>();
        for (Object o : data.getMap().keySet()) {
            String valMap = data.getMap().get(o).toString();
            String[] split = valMap.split(":");
            array.add(format(o, split[0], split[1]));
        }
        return array;
    }

    /**
     * Append everything to a string, then do whatever
     *
     * @param data
     * @return
     */
    public String formatData(DataObject data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : data.getMap().keySet()) {
            String valMap = data.getMap().get(o).toString();
            String[] split = valMap.split(":");
            stringBuilder.append(format(o, split[0], split[1]));
        }
        return stringBuilder.toString();
    }


    /**
     * Used to format the final String
     *
     * @param o
     * @param key
     * @param val
     * @return
     */
    private String format(Object o, String key, String val) {
        return "{" + o.getClass().getSimpleName() + StringUtil.splitter + key + StringUtil.splitter + val + "}";
    }

    /**
     * Decode data and give value for specific key.
     *
     * @param keyIn
     * @return
     */
    public Object decode(String keyIn) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String value = getValueFromKey(keyIn);
            DataType dataType = getDataType(getData(keyIn));
            if (dataType == DataType.ARRAY || dataType == DataType.STRING || dataType == DataType.CHARACTER) {
                return stringBuilder.append(value).toString();
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
     * TODO: Rewrite this
     *
     * @param file
     * @param keyIn
     * @return
     */
    @Deprecated
    public Object[] decodeToArray(File file, String keyIn) {
        List<Object> re = new ArrayList<>();
        try {
            String value = getValueFromKey(keyIn);
            DataType dataType = getDataType(getData(keyIn));

            if (dataType == DataType.ARRAY) {
                //     for (int i = 0; i < getArray(line).length; i++) {
                //       re.add(getArray(line)[i]);
                //    }
            } else if (dataType == DataType.STRING || dataType == DataType.CHARACTER) {
                re.add(value);
            } else {
                re.add(ClassUtil.getMethod(dataType.aClass, "valueOf", new Class[]{String.class}).invoke(dataType.aClass, value));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return re.toArray();
    }

    /**
     * @param keyIn
     * @return
     */
    private String getValueFromKey(String keyIn) {
        return getString(keyIn).split(StringUtil.splitter)[2];
    }

    /**
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
    @Hidden
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
            int blockSize = StringUtil.getTotalCharInString(line, '}');
            //new stringbuilder for easier deletion
            StringBuilder builderLine = new StringBuilder(line);
            //For every block
            for (int i = 0; i < blockSize; i++) {
                //Get block bounds
                int[] blockBounds = getBlock(builderLine.toString());
                //Add inner block to blocks array
                blocks.add(getInnerBlock(builderLine.toString()));
                //Delete old block from builder to new once can be found
                builderLine.delete(blockBounds[0], blockBounds[1]);
            }
        }

        //Return block array
        return blocks;
    }

    /**
     * Get block bounds
     * {             }
     * 1             2
     *
     * @param string
     * @return
     */
    private int[] getBlock(String string) {
        int begin = string.indexOf("{");
        int end = string.indexOf("}");
        return new int[]{begin, end + 1};
    }

    /**
     * Get inner block
     *
     * @param string
     * @return
     */
    private String getInnerBlock(String string) {
        int[] block = getBlock(string);
        return string.substring(block[0] + 1, block[1] - 1);
    }

    /**
     * Formats intended array String back to array
     *
     * @param s
     * @return
     */
    @Deprecated
    private Object[] getArray(String s) {
        String s1 = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
        String[] split = s1.split(",");
        Object[] array = new Object[split.length];
        for (int i = 0; i < split.length; i++) {
            array[i] = split[i];
        }
        return array;
    }

    /**
     * TODO: Change
     * @param s
     * @return
     */
    private DataType getDataType(String s) {
        for (DataType value : DataType.values()) {
            if (s.equalsIgnoreCase(value.getType())) return value;
        }
        return null;
    }

    public void setNewLine(boolean newLine) {
        this.newLine = newLine;
    }

    /**
     *
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
