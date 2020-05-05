/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;

import ga.abzzezz.util.array.ArrayUtil;
import ga.abzzezz.util.data.FileUtil;
import ga.abzzezz.util.stringing.StringUtil;

import java.io.File;
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
        List<String> lines = FileUtil.getFileContentAsList(file);
        String line = lines.get(ArrayUtil.indexOfKeyword(lines, keyIn));
        String[] split = getKeyAndValue(line).split(StringUtil.splitter);
        String value = split[1];
        if (split[0].equalsIgnoreCase(keyIn)) {
            if (line.startsWith("ArrayList")) {
                stringBuilder.append(getArray(line));
            } else {
                stringBuilder.append(value);
            }
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
        List<String> lines = FileUtil.getFileContentAsList(file);
        String line = lines.get(ArrayUtil.indexOfKeyword(lines, keyIn));
        String[] split = getKeyAndValue(line).split(StringUtil.splitter);
        String value = split[1];
        if (split[0].equalsIgnoreCase(keyIn)) {
            if (line.startsWith("ArrayList")) {
                for (int i = 0; i < getArray(line).length; i++) {
                    re.add(getArray(line)[i]);
                }
            } else {
                re.add(value);
            }
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

    /**
     * Will be used soon
     */
    private enum DataType {
        STRING, ARRAY, INTEGER, LONG, DOUBLE, FLOAT, BYTE, SHORT, CHARACTER
    }
}
