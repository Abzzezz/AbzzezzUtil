/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.array;

import ga.abzzezz.util.data.ClassUtil;
import ga.abzzezz.util.exceptions.KeyNotFoundException;
import ga.abzzezz.util.stringing.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayUtil {

    /**
     * Really efficient, new method
     *
     * @param in
     * @return
     */

    public static ArrayList<String> sortWithNumberInName(ArrayList<String> in) {
        Collections.sort(in, Comparator.comparingInt(StringUtil::extractNumberint));
        return in;
    }

    /**
     * @param in
     * @param split
     * @param arrayIndex
     * @return
     */
    public static ArrayList<String> sortWithNumberInName(ArrayList<String> in, String split, int arrayIndex) {
        Collections.sort(in, Comparator.comparingInt(o -> StringUtil.extractNumberint(o.split(split)[arrayIndex])));
        return in;
    }

    public static void removeElement(Object[] arr, int removedIdx) {
        System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
    }

    public static List<Object> convertStringToObjectArray(List<String> in, Class o) {
        List<Object> newArray = new ArrayList<>();
        for (String s : in) {
            try {
                newArray.add(ClassUtil.getMethod(o, "valueOf", new Class[]{String.class}).invoke(o, s));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return newArray;
    }

    /**
     * Only works if keyword only exists one time! Otherwise the last index of the keyword appearing will be returned
     *
     * @param in
     * @param keyword
     * @return
     */
    public static int indexOfKeyword(List<String> in, String keyword) {
        int index = -1;
        for (int i = 0; i < in.size(); i++) {
            if (in.get(i).contains(keyword)) index = i;
        }
        if (index == -1) {
            try {
                throw new KeyNotFoundException();
            } catch (KeyNotFoundException e) {
                e.printStackTrace();
            }
        }
        return index;
    }
}
