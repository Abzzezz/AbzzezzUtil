package ga.abzzezz.util.data;

import ga.abzzezz.util.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class URLUtil {

    public static String getURLContentAsString(URL url) {
        String out = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                out = out + line;
            }
            bufferedReader.close();
            return out;
        } catch (IOException e) {
            Logger.log("Opening stream to url: " + url.getPath(), Logger.LogType.ERROR);
        }

        return "NULL";
    }


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
            Logger.log("Opening stream to url: " + url.getPath(), Logger.LogType.ERROR);
        }

        return null;
    }

    public static String toUrl(String in, String protocol) {
        protocol = protocol + "://";
        return protocol + in;
    }

}
