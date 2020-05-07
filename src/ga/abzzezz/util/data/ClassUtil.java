/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data;

import java.lang.reflect.Method;

public class ClassUtil {

    /**
     * Get Method
     * @param c
     * @param method
     * @param mLenght
     * @param type
     * @return
     */
    public static Method getMethod(Class c, String method, int mLenght, Class type) {
        for (int i = 0; i < c.getMethods().length; i++) {
            if (c.getMethods()[i].getName().equalsIgnoreCase(method)) {
                if (c.getMethods()[i].getParameters().length == mLenght && c.getMethods()[i].getParameters()[0].getType() == type) {
                    Method m = c.getMethods()[i];
                    return m;
                }
            }
        }
        return null;
    }
}