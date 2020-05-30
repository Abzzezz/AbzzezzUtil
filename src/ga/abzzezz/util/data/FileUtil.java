/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data;

import ga.abzzezz.util.exceptions.WritingtoFileException;
import ga.abzzezz.util.logging.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.List;

public class FileUtil {

    /**
     * Gets file content as list
     *
     * @param file
     * @return
     */
    public static List<String> getFileContentAsList(File file) throws MalformedURLException {
        return URLUtil.getURLContentAsArray(file.toURI().toURL());
    }


    /**
     * @param file
     * @return
     */
    public static String getFileContentAsString(File file) throws MalformedURLException {
        return URLUtil.getURLContentAsString(file.toURI().toURL());
    }

    /**
     * @param in
     * @param out
     * @param append
     * @param newLine
     */
    public static void writeArrayListToFile(List<String> in, File out, boolean append, boolean newLine) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out, append));
            in.forEach(s -> {
                try {
                    bufferedWriter.write(s);
                    if (newLine) bufferedWriter.newLine();
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
     * @param append
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
            byte[] buffer = new byte[4194304];
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

    /**
     * Copy file from url method. Reads bytes from url and stores them into a file
     *
     * @param outFile
     * @param inURL
     */
    public static void copyFileFromURL(File outFile, String inURL) {
        try {
            InputStream inputStream = new BufferedInputStream(new URL(inURL).openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));
            //4mb
            byte[] buffer = new byte[4194304];
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

    /**
     * @param file
     * @return
     */
    public static String calculateFileSize(File file) {
        try {

            long fileSize = Files.walk(file.toPath())
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();

            return getReadableSize(fileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    /**
     * Credit: Stackoverflow: https://stackoverflow.com/questions/3263892/format-file-size-as-mb-gb-etc/5599842#5599842
     *
     * @param size
     * @return
     */
    public static String getReadableSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups))
                + " " + units[digitGroups];
    }

    /**
     * Deletes file
     *
     * @param toDelete
     */
    public static void deleteFile(File toDelete) {
        if (toDelete.exists()) {
            Logger.log("File delete state:" + toDelete.delete(), Logger.LogType.INFO);
        }
    }
}
