package ga.abzzezz.util.stringing;

import ga.abzzezz.util.logging.Logger;

public class StringUtil {

    /*
    Needed for all stringing
     */
    public static String splitter = ":::";



    public static String getStringFromLong(String in, String toSearch, char endModifire) {
        int startIndex = in.indexOf(toSearch) + toSearch.length();
        return in.substring(startIndex, getIndexOfCharInStringFromIndex(in, startIndex, endModifire));
    }

    public static int getIndexOfCharInStringFromIndex(String in, int startIndex, char find) {
        try {
            char[] inCharArray = in.toCharArray();
            int r = 0;
            for (int i = startIndex; i < in.length(); i++) {
                if (inCharArray[i] == find) {
                    r = i;
                    break;
                }
            }
            return r;
        } catch (ArrayIndexOutOfBoundsException e) {
            Logger.log("Stringing", Logger.LogType.INFO);
        }
        return 0;
    }

    public static String removenotallowedCharacters(String in) {
        String[] toRemove = {"\\\\", ":", "\""};
        for (int i = 0; i < toRemove.length; i++) {
            in = in.replaceAll(toRemove[i], "");
        }
        return in;
    }

    public static String extractNumber(final String str) {
        if(str == null || str.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : str.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } else if(found){
                break;
            }
        }

        return sb.toString();
    }
}
