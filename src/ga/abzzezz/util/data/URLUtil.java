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
import java.net.URL;
import java.net.URLConnection;
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connectionWithAgent(url).getInputStream()));
            bufferedReader.lines().forEach(stringBuilder::append);
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NULL";
    }

    private static URLConnection connectionWithAgent(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        urlConnection.connect();

        return urlConnection;
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connectionWithAgent(url).getInputStream()));
            bufferedReader.lines().forEach(out::add);
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
    @Deprecated
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
    public static String extractDomainFromURL(URL url) {
        return url.getHost();
    }
}
