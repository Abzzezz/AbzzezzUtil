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
import ga.abzzezz.util.stringing.StringUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DataFormat {

    /**
     * Data will be formatted to the "Abzzezz util data format"
     *
     * @param data
     * @return
     */

    public static void formatData(DataObject data, File file, boolean append) {
        List<String> array = new ArrayList<>();
        for (Object o : data.getMap().keySet()) {
            String valMap = data.getMap().get(o).toString();
            String[] split = valMap.split(":");
            String key = split[0];
            String value = split[1];
            array.add(format(o, key, value));
        }
        FileUtil.writeArrayListToFile(array, file);
    }

    /**
     * Used to format the final String
     *
     * @param o
     * @param key
     * @param val
     * @return
     */
    private static String format(Object o, String key, String val) {
        return o.getClass().getSimpleName() + "{" + key + StringUtil.splitter + val + "}";
    }

    /**
     * Decode data and give value for specific key.
     *
     * @param file
     * @param keyIn
     * @return
     */
    public static Object decode(File file, String keyIn) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            List<String> lines = FileUtil.getFileContentAsList(file);
            String line = lines.get(ArrayUtil.indexOfKeyword(lines, keyIn));
            String[] split = getKeyAndValue(line).split(StringUtil.splitter);
            String value = split[1];
            if (split[0].equalsIgnoreCase(keyIn)) {
                DataType dataType = getDataType(line);
                if (dataType == DataType.ARRAY) {
                    stringBuilder.append(getArray(line));
                } else if (dataType == DataType.STRING || dataType == DataType.CHARACTER) {
                    stringBuilder.append(value);
                } else {
                    return ClassUtil.getMethod(dataType.aClass, "valueOf",  new Class[]{String.class}).invoke(dataType.aClass, value);
                }
            }
        } catch (IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * Probably extremely inefficient. Will change soon
     *
     * @param file
     * @param keyIn
     * @return
     */
    public static Object[] decodeToArray(File file, String keyIn) {
        List<Object> re = new ArrayList<>();
        try {
            List<String> lines = FileUtil.getFileContentAsList(file);
            String line = lines.get(ArrayUtil.indexOfKeyword(lines, keyIn));
            String[] split = getKeyAndValue(line).split(StringUtil.splitter);
            String value = split[1];
            if (split[0].equalsIgnoreCase(keyIn)) {
                DataType dataType = getDataType(line);
                if (dataType == DataType.ARRAY) {
                    for (int i = 0; i < getArray(line).length; i++) {
                        re.add(getArray(line)[i]);
                    }
                } else if (dataType == DataType.STRING || dataType == DataType.CHARACTER) {
                    re.add(value);
                } else {
                    re.add(ClassUtil.getMethod(dataType.aClass, "valueOf", new Class[]{String.class}).invoke(dataType.aClass, value));
                }
            }
        } catch (IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return re.toArray();
    }

    /**
     * @param s
     * @return
     */
    private static String getKeyAndValue(String s) {
        int begin = s.indexOf("{");
        int end = s.indexOf("}");
        return s.substring(begin + 1, end);
    }

    /**
     * Formats intended array String back to array
     *
     * @param s
     * @return
     */
    private static Object[] getArray(String s) {
        int begin = s.indexOf("[");
        int end = s.indexOf("]");
        String s1 = s.substring(begin + 1, end);
        String[] split = s1.split(",");
        List<Object> re = new ArrayList<>();
        for (int i = 0; i < split.length; i++) re.add(split[i]);
        return re.toArray();
    }

    private static DataType getDataType(String s) {
        for (DataType value : DataType.values()) {
            if (s.startsWith(value.getType())) {
                return value;
            }
        }
        return null;
    }

    @Deprecated
    private static boolean isTypeNumber(DataType dataType) {
        return dataType == DataType.INTEGER
                || dataType == DataType.FLOAT || dataType == DataType.LONG || dataType == DataType.DOUBLE
                || dataType == DataType.BYTE || dataType == DataType.SHORT;
    }

    /**
     * Will be used soon
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
