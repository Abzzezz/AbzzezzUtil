/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.stringing;

import ga.abzzezz.util.exceptions.StringingException;

import java.util.ArrayList;

public class StringUtil {

    /**
     * Needed for all stringing
     *
     */
    public static String splitter = ":::";

    /**
     * Substring from a point
     * @param in
     * @param toSearch
     * @param endModifire
     * @return
     */
    public static String getStringFromLong(String in, String toSearch, char endModifire) {
        int startIndex = in.indexOf(toSearch) + toSearch.length();
        return in.substring(startIndex, getIndexOfCharInStringFromIndex(in, startIndex, endModifire));
    }

    /**
     * @param in
     * @param startIndex
     * @param find
     * @return
     */
    public static int getIndexOfCharInStringFromIndex(String in, int startIndex, char find) {
        return in.indexOf(find, startIndex);
    }

    /**
     * Get the number of a char appearing in a string
     * @param in
     * @param c
     * @return
     */
    public static int getTotalCharInString(String in, char c) {
        int counter = 0;
        for (char c1 : in.toCharArray()) {
            if (c1 == c) {
                counter += 1;
            }
        }
        return counter;
    }

    /**
     * @param in
     * @return
     */
    public static String removenotallowedCharacters(String in) {
        String[] toRemove = {"\\\\", ":", "\""};
        for (int i = 0; i < toRemove.length; i++) {
            in = in.replaceAll(toRemove[i], "");
        }
        return in;
    }

    /**
     * Remove all defined not allowed characters from a string
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
     * @param str
     * @return
     */
    public static String extractNumber(String str) {
        if (str == null || str.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
                break;
            }
        }
        return sb.toString();
    }

    /**
     * @param str
     * @return
     */
    public static ArrayList<String> getAllNumbersInAString(String str) {
        if (str == null || str.isEmpty()) return null;
        ArrayList<String> out = new ArrayList<>();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) out.add(String.valueOf(c));
        }
        return out;
    }
}
