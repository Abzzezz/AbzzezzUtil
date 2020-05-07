/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data;

import ga.abzzezz.util.exceptions.WritingtoFileException;
import ga.abzzezz.util.logging.Logger;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * @param file
     * @return
     */
    public static List<String> getFileContentAsList(File file) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.log("Reading content from file: " + file + "  " + e.getMessage(), Logger.LogType.ERROR);
        }
        return lines;
    }

    /**
     * @param in
     * @param out
     */
    public static void writeArrayListToFile(List<String> in, File out) {
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
            try {
                throw new WritingtoFileException();
            } catch (WritingtoFileException writingtoFileException) {
                writingtoFileException.printStackTrace();
            }
            Logger.log("Checking file: " + out + "  " + e.getMessage(), Logger.LogType.ERROR);
        }
    }

    /**
     * @param in
     * @param out
     */
    public static void appendToFile(String in, File out, boolean append) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out, append));
            bufferedWriter.write(in);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            try {
                throw new WritingtoFileException();
            } catch (WritingtoFileException writingtoFileException) {
                writingtoFileException.printStackTrace();
            }
            Logger.log("Writing to file: " + out + "  " + e.getMessage(), Logger.LogType.ERROR);
        }
    }

    /**
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

    public static void copyFileFromURL(File outFile, String inURL) {
        try {
            InputStream inputStream = new BufferedInputStream(new URL(inURL).openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
            inputStream.close();
            out.close();
        } catch (IOException e) {
            Logger.log("Creating copying File from URL: " + inURL, Logger.LogType.ERROR);
        }
    }

    public static void deleteFile(File toDelete) {
        if (toDelete.exists()) {
            toDelete.deleteOnExit();
            Logger.log("File delete state:" + toDelete.delete(), Logger.LogType.INFO);
        }
    }
}
