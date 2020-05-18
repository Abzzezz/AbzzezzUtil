/*
 * @author Roman P.
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

/**
 * Used to handle array tasks such as sorting, deleting elements etc.
 */
public class ArrayUtil {

    /**
     * Uses Comparator comparing
     * @see Comparator
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
        //if split length < arrayIndex sort 0 to avoid errors
        Collections.sort(in, Comparator.comparingInt(o -> StringUtil.extractNumberint((o.split(split).length > arrayIndex) ? o.split(split)[arrayIndex] : "0")));
        return in;
    }

    public static void removeElement(Object[] arr, int removedIdx) {
        System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
    }

    /**
     * @param in
     * @param o
     * @return
     */
    @Deprecated
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
    public static int indexOfKey(List<String> in, String keyword) {
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

    /**
     * Same as the list method, uses arrays instead
     * @param in
     * @param keyword
     * @return
     */
    public static int indexOfKey(String[] in, String keyword) {
        int index = -1;
        for (int i = 0; i < in.length; i++) {
            if (in[i].contains(keyword)) index = i;
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
