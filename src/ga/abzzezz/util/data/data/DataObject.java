/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Data Object
 */
public class DataObject {

    /**
     * Object Type :: key and value
     */
    private final HashMap<Object, Object> map;

    public DataObject() {
        this.map = new HashMap<>();
    }

    public void addObject(Object key, Object value) {
        this.map.put(value, key + ":" + value);
    }

    public void addList(String key, List<?> in) {
        this.map.put(new ArrayList<>(in), key + ":" + in.toString());
    }

    public void addArray(String key, Object[] in) {
        this.map.put(in, key + ":" + Arrays.toString(in));
    }

    public HashMap<Object, Object> getMap() {
        return map;
    }
}
