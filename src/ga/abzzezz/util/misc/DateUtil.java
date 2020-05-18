/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.misc;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateUtil {

    /**
     *
     * @return
     */
    public static String getCurrentDay() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }

    /**
     *
     * @return
     */
    public static String getTimeAndMS() {
        LocalTime localTime = LocalTime.now();
        return localTime.toString();
    }
}
