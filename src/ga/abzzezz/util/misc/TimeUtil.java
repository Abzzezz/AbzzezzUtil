/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.misc;

public class TimeUtil {

    private long time;

    /**
     * @param timeOver
     * @return
     */
    public boolean isTimeOver(long timeOver) {
        return getSystemTime() - time > timeOver;
    }

    public void reset() {
        time = getSystemTime();
    }

    public long getSystemTime() {
        return System.currentTimeMillis();
    }

}
