/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.array;

import ga.abzzezz.util.stringing.StringUtil;

import java.io.IOException;
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
    public static List<String> sortWithNumberInName(List<String> in) {
        in.sort(Comparator.comparingInt(StringUtil::extractNumberI));
        return in;
    }

    /**
     * Sort with number in string. Used if number is not easily accessible
     *
     * @param in
     * @param split
     * @param arrayIndex
     * @return
     */
    public static List<String> sortWithNumberInName(List<String> in, String split, int arrayIndex) {
        //if split length < arrayIndex sort 0 to avoid errors
        in.sort(Comparator.comparingInt(o -> StringUtil.extractNumberI((o.split(split).length > arrayIndex) ? o.split(split)[arrayIndex] : "0")));
        return in;
    }



    /**
     * Streams list and returns the first index of the keyword found
     *
     * @param in
     * @param keyword
     * @return
     */
    public static int indexOfKey(List<String> in, String keyword) {
        try {
            return in.indexOf(in.stream().filter(s -> s.contains(keyword)).findFirst().get());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Same as the list method, uses arrays instead
     *
     * @param in
     * @param keyword
     * @return
     */
    public static int indexOfKey(String[] in, String keyword) {
        return indexOfKey(Arrays.asList(in), keyword);
    }
}
