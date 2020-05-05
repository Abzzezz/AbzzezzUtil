/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.animations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: trey & Abzzezz
 * @created on: 22.07.2018 16:25:46
 */

public class AnimationUtil {

    public float velocity, oppositeVelocity, min, max, step;

    public float time;

    public boolean animated, reversed;

    public String type;

    private final Class<?> classToUse;

    public AnimationUtil(final Class<?> classToUse, final float velocity, final float min, final float max, final float step, final boolean animated, final boolean reversed) {
        this.velocity = velocity;
        this.oppositeVelocity = (MathUtil.nabs(velocity));
        this.min = min;
        this.max = max;
        this.step = step;
        this.animated = animated;
        this.reversed = reversed;
        this.classToUse = classToUse;
        if (reversed)
            time = (int) max;
        else
            time = (int) min;
    }

    public void animate() {
        if (reversed) {
            if (time > min) {
                time -= step;
            }
        } else {
            if (time < max) {
                time += step;
            }
        }

        try {
            Class[] c = new Class[4];
            c[0] = float.class;
            c[1] = float.class;
            c[2] = float.class;
            c[3] = float.class;

            Method m = classToUse.getMethod("easeInOut", c);

            this.velocity = (float) m.invoke(classToUse, time, min, max, max);
            this.oppositeVelocity = MathUtil.nabs((float) m.invoke(classToUse, time, min, max, max));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        }

    }

    public void reset() {
        if (reversed) time = max;
        else time = min;

        velocity = min;
        oppositeVelocity = (MathUtil.nabs(velocity));
    }

    public int getInt() {
        return (int) velocity;
    }

    public float getFloat() {
        return velocity;
    }

    public double getDouble() {
        return velocity;
    }
}
