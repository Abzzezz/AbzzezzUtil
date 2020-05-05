/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;

import java.util.HashMap;
import java.util.List;

public class DataObject {
    /**
     *
     */

    /**
     * Object Type :: key and value
     */
    private final HashMap<Object, Object> map;

    public DataObject() {
        this.map = new HashMap<>();
    }

    public void addObject(Object key, Object value) {
        this.map.put(key, key + ":" + value);
    }

    public void addList(List<?> in, String key) {
        this.map.put(in, key + ":" + in.toString());
    }

    public HashMap<Object, Object> getMap() {
        return map;
    }
}
