/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.logging;

import javax.swing.*;

/**
 * Own logger to simply log
 */
public class Logger {

    public static String logSwingInput(String in) {
        return JOptionPane.showInputDialog(in);
    }

    public static void log(String message, LogType type) {
        System.out.println("[" + type.getName() + "] " + message);
    }

    /**
     * Log types
     */
    public enum LogType {
        ERROR, INFO, WARNING, NONE;
        public String getName() {
            return this.toString();
        }
    }
}
