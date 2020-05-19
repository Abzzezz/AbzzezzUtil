/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data;

import ga.abzzezz.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class URLUtil {

    /**
     * Get url content as String
     *
     * @param url
     * @return
     */
    public static String getURLContentAsString(URL url) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            bufferedReader.lines().forEach(line -> stringBuilder.append(line));

            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    /**
     * Stores all lines from an url into an array
     *
     * @param url
     * @return
     */
    public static ArrayList<String> getURLContentAsArray(URL url) {
        ArrayList<String> out = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            bufferedReader.lines().forEach(s -> out.add(s));
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.log("Opening stream to url: " + url.getPath(), Logger.LogType.ERROR);
        }
        return out;
    }

    /**
     * Convert string to url
     *
     * @param in
     * @param protocol
     * @return
     */
    public static String toUrl(String in, String protocol) {
        protocol = protocol + "://";
        return protocol + in;
    }

    /**
     * Get domain from url
     *
     * @param url
     * @return
     */
    public static String extractDomainFromURL(String url) {
        try {
            return new URL(url).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
