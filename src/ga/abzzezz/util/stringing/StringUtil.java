/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.stringing;

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
     * @param endModifier
     * @return
     */
    public static String getStringFromLong(String in, String toSearch, String endModifier) {
        int startIndex = in.indexOf(toSearch) + toSearch.length();
        return in.substring(startIndex, in.indexOf(endModifier, startIndex));
    }

    /**
     * count all appearing char instances in a string
     *
     * @param in
     * @param c
     * @return
     */
    public static int getTotalCharInString(String in, char c) {
        return (int) in.chars().filter(value -> value == c).count();
    }

    /**
     * Remove all in Windows not allowed characters
     *
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
     *
     * @param str
     * @return
     * @see ga.abzzezz.util.array.ArrayUtil
     */
    public static String extractNumber(String str) {
        return str.replaceAll("[^0-9\\.]", "");
    }

    /**
     * Returns int instead of string
     * @param str
     * @return
     */
    public static int extractNumberI(String str) {
        return Integer.valueOf(extractNumber(str));
    }
}
