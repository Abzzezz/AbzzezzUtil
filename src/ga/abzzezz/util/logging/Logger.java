/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.logging;

import javax.swing.*;

public class Logger {

    public static String logSwingInput(String in) {
        return JOptionPane.showInputDialog(in);
    }

    public static void log(String message, LogType type) {
        System.out.println("[" + type.getName() + "] " + message);
    }

    public enum LogType {
        ERROR("ERROR"), INFO("INFO"), WARNING("WARNING"), NONE("NONE");

        String name;

        LogType(String out) {
            name = out;
        }

        public String getName() {
            return name;
        }
    }
}
