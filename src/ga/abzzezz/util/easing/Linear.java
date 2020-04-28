/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.easing;

public class Linear {

    public static float easeNone(float t, float b, float c, float d) {
        return c * t / d + b;
    }

    public static float easeIn(float t, float b, float c, float d) {
        return c * t / d + b;
    }

    public static float easeOut(float t, float b, float c, float d) {
        return c * t / d + b;
    }

    public static float easeInOut(float t, float b, float c, float d) {
        return c * t / d + b;
    }

}
