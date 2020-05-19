/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.array;

import ga.abzzezz.util.data.ClassUtil;
import ga.abzzezz.util.stringing.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Used to handle array tasks such as sorting, deleting elements etc.
 */
public class ArrayUtil {

    /**
     * Uses Comparator comparing
     *
     * @param in
     * @return
     * @see Comparator
     */
    public static ArrayList<String> sortWithNumberInName(ArrayList<String> in) {
        Collections.sort(in, Comparator.comparingInt(StringUtil::extractNumber));
        return in;
    }

    /**
     * Sort with number in string. Used if number is not easily accessible
     * @param in
     * @param split
     * @param arrayIndex
     * @return
     */
    public static ArrayList<String> sortWithNumberInName(ArrayList<String> in, String split, int arrayIndex) {
        //if split length < arrayIndex sort 0 to avoid errors
        Collections.sort(in, Comparator.comparingInt(o -> StringUtil.extractNumber((o.split(split).length > arrayIndex) ? o.split(split)[arrayIndex] : "0")));
        return in;
    }

    /**
     * Remove element from array
     * @param arr
     * @param removedIdx
     */
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
     * Streams list and returns the first index of the keyword found
     * @param in
     * @param keyword
     * @return
     */
    public static int indexOfKey(List<String> in, String keyword) {
        return in.indexOf(in.stream().filter(s -> s.contains(keyword)).findFirst().get());
    }

    /**
     * Same as the list method, uses arrays instead
     * @param in
     * @param keyword
     * @return
     */
    public static int indexOfKey(String[] in, String keyword) {
        return indexOfKey(Arrays.asList(in), keyword);
    }
}
