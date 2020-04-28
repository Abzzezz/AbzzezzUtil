/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.logging;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Logger {

    public static Object getObjectsFormConsole() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Object re = null;
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                re = line;
            }

        } catch (IOException e) {
            log("Error reading console", LogType.ERROR);
        }

        return re;
    }

    public static void logError(String where) {
        EventQueue.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Error occurred: " + where);
        });
    }

    public static void logConsole(String name, String in) {
        System.out.println(name + ": " + in);
    }

    public static void logFrame(String in) {
        EventQueue.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, in);
        });
    }

    public static String logInput(String in) {
        return JOptionPane.showInputDialog(in);
    }

    public static void log(String message, LogType type) {
        System.out.println("[" + type.getName() + "] " + message);
    }

    public enum LogType {
        ERROR("ERROR"), INFO("INFO"), WARNING("WARNING");

        String name;

        LogType(String out) {
            name = out;
        }

        public String getName() {
            return name;
        }
    }
}
