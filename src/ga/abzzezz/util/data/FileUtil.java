/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data;

import ga.abzzezz.util.logging.Logger;

import java.io.*;
import java.nio.file.Files;
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

    /**
     * @param in
     * @param out
     */
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

    /**
     * @param in
     * @param out
     */
    public static void writeArrayListToFile(ArrayList<String> in, File out) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out));
            in.forEach(s -> {
                try {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    Logger.log("Writing to file: " + out + "  " + e.getMessage(), Logger.LogType.ERROR);
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

    /**
     *
     * @param in
     * @param dest
     * @param delete
     */
    public static void copyFile(File in, File dest, boolean delete) {
        try {
            dest.createNewFile();
            InputStream inputStream = new BufferedInputStream(new FileInputStream(in));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = Files.readAllBytes(in.toPath());
            int lengthRead;
            while ((lengthRead = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
            inputStream.close();
            out.close();

            if (delete) Files.delete(in.toPath());
        } catch (IOException e) {
            Logger.log("Creating new file", Logger.LogType.ERROR);
        }
    }
}
