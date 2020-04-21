package ga.abzzezz.util.data;

import ga.abzzezz.util.logging.Logger;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {

    public static ArrayList<String> getFileContentAsList(File file) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            Logger.log("Reading content from file: " + file + "  " + e.getMessage(), Logger.LogType.ERROR);
        }
        return lines;
    }

    public static void writeToFile(String in, File out) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out));
            bufferedWriter.write(in);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            Logger.log("Writing to file: " + out + "  " + e.getMessage(), Logger.LogType.ERROR);
        }
    }


    public static void writeArrayToFile(ArrayList<String> in, File out) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out));
            in.forEach(s -> {
                try {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    Logger.logError("Writing to file: " + out + "  " + e.getMessage());
                }
            });
            bufferedWriter.close();
        } catch (IOException e) {
            Logger.log("Checking file: " + out + "  " + e.getMessage(), Logger.LogType.ERROR);
        }
    }


    public static void addToFile(String in, File out) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out));
            bufferedWriter.append(in);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            Logger.logError("Writing to file: " + out + "  " + e.getMessage());
        }
    }
}
