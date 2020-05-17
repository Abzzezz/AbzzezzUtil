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
     * Get method
     *
     * @param c
     * @param method
     * @param params
     * @return
     */
    public static Method getMethod(Class c, String method, Class[] params) {
        Method m = null;
        try {
            m = c.getDeclaredMethod(method, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return m;

    }
}