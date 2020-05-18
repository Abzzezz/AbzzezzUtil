/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.stringing;

import java.util.ArrayList;

/**
 * Used for all types of String work
 */
public class StringUtil {

    /**
     * Needed for all stringing
     */
    public static String splitter = ":::";

    /**
     * Substring from a point
     *
     * @param in
     * @param toSearch
     * @param endModifire
     * @return
     */
    public static String getStringFromLong(String in, String toSearch, char endModifire) {
        int startIndex = in.indexOf(toSearch) + toSearch.length();
        return in.substring(startIndex, in.indexOf(startIndex, endModifire));
    }

    /**
     * Get the number of a char appearing in a string
     *
     * @param in
     * @param c
     * @return
     */
    public static int getTotalCharInString(String in, char c) {
        return (int) in.chars().filter(value -> value == c).count();
    }

    /**
     * @param in
     * @return
     */
    public static String removeWindowsChars(String in) {
        String[] toRemove = {"\\\\", ":", "\""};
        for (int i = 0; i < toRemove.length; i++) {
            in = in.replaceAll(toRemove[i], "");
        }
        return in;
    }

    /**
     * Remove all defined not allowed characters from a string
     *
     * @param in
     * @param array
     * @return
     */
    public static String removenotallowedCharacters(String in, String[] array) {
        for (int i = 0; i < array.length; i++) {
            in = in.replaceAll(array[i], "");
        }
        return in;
    }

    /**
     * Extracts numbher from string
     * @see ga.abzzezz.util.array.ArrayUtil
     * @param str
     * @return
     */
    public static String extractNumber(String str) {
        return str.replaceAll("[^0-9\\.]", "");
    }

    /**
     * Returns number instead of string
     * @param str
     * @return
     */
    public static int extractNumberint(String str) {
        return Integer.valueOf(extractNumber(str));
    }

    /**
     * Inefficient method also only works with numbers 0 - 9
     * @param str
     * @return
     */
    @Deprecated
    public static ArrayList<String> getAllNumbersInAString(String str) {
        if (str == null || str.isEmpty()) return null;
        ArrayList<String> out = new ArrayList<>();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) out.add(String.valueOf(c));
        }
        return out;
    }
}
