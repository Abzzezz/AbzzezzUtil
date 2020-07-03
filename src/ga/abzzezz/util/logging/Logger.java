/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.logging;


/**
 * Own logger to simply log
 */
public class Logger {

    public static <E> void log(E message, LogType type) {
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
