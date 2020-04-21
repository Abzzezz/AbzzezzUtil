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
