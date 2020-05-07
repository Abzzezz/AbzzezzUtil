/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data;

import ga.abzzezz.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class URLUtil {

    /**
     * Get url content as String
     * @param url
     * @return
     */
    public static String getURLContentAsString(URL url) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    /**
     *
     * @param url
     * @return
     */
    public static ArrayList<String> getURLContentAsArray(URL url) {
        ArrayList<String> out = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                out.add(line);
            }
            bufferedReader.close();
            return out;
        } catch (IOException e) {
            e.printStackTrace();
            Logger.log("Opening stream to url: " + url.getPath(), Logger.LogType.ERROR);
        }
        return null;
    }

    /**
     *
     * @param in
     * @param protocol
     * @return
     */
    public static String toUrl(String in, String protocol) {
        protocol = protocol + "://";
        return protocol + in;
    }

}
