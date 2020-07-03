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
import java.nio.channels.Channels;
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
        return URLUtil.getURLContentAsList(file.toURI().toURL());
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
    public static <E> void writeArrayListToFile(List<E> in, File out, boolean append, boolean newLine) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out, append));
            in.forEach(s -> {
                try {
                    bufferedWriter.write(String.valueOf(s));
                    if (newLine) bufferedWriter.newLine();
                } catch (IOException e) {
                    try {
                        throw new WritingtoFileException();
                    } catch (WritingtoFileException writingtoFileException) {
                        writingtoFileException.printStackTrace();
                    }
                    Logger.log("Writing to file: " + out + "  " + e.getMessage(), Logger.LogType.ERROR);
                }
            });
            bufferedWriter.close();
        } catch (IOException e) {
            Logger.log("Checking file: " + out + "  " + e.getMessage(), Logger.LogType.ERROR);
        }
    }

    /**
     * @param in
     * @param out
     * @param append
     */
    public static <E> void appendToFile(E in, File out, boolean append) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(out, append));
            bufferedWriter.write(String.valueOf(in));
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
    public static boolean copyFile(File in, File dest, boolean delete) {
        try {
            if (!dest.exists()) dest.createNewFile();
            InputStream inputStream = new BufferedInputStream(new FileInputStream(in));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
            inputStream.close();
            out.close();
            if (delete) Files.delete(in.toPath());
            return true;
        } catch (IOException e) {
            Logger.log("Creating new file" + e.getMessage(), Logger.LogType.ERROR);
            return false;
        }
    }

    /**
     * Copy file from url method. Reads bytes from url and stores them into a file
     *
     * @param dest
     * @param inURL
     */
    public static boolean copyFileFromURL(File dest, String inURL) {
        try {
            if (!dest.exists()) dest.createNewFile();
            InputStream inputStream = new BufferedInputStream(new URL(inURL).openStream());
            FileOutputStream out = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inputStream.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, lengthRead);
            }
            out.flush();
            inputStream.close();
            out.close();
            return true;
        } catch (IOException e) {
            Logger.log("Copying File from URL: " + inURL + e.getMessage(), Logger.LogType.ERROR);
            return false;
        }
    }

    public static boolean copyFileFromURL(File dest, URL in) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dest);
            fileOutputStream.getChannel().transferFrom(Channels.newChannel(in.openStream()), 0, Long.MAX_VALUE);
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            Logger.log("Copying file from url: " + e.getMessage(), Logger.LogType.INFO);
            e.printStackTrace();
            return false;
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
