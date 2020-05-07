/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.misc;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateUtil {

    public static String getCurrentDay() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }

    public static String getTimeAndMS() {
        LocalTime localTime = LocalTime.now();
        return localTime.toString();
    }
}
