/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.easing;

public class Quad {

    public static float easeIn(float t, float b, float c, float d) {
        return c * (t /= d) * t + b;
    }

    public static float easeOut(float t, float b, float c, float d) {
        return -c * (t /= d) * (t - 2) + b;
    }

    public static float easeInOut(float t, float b, float c, float d) {
        if ((t /= d / 2) < 1) return c / 2 * t * t + b;
        return -c / 2 * ((--t) * (t - 2) - 1) + b;
    }

}
