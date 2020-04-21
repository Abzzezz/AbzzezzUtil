package ga.abzzezz.util.misc;

public class TimeUtil {

    private long time;

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
