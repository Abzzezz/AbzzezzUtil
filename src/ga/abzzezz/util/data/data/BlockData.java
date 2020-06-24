/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;

public class BlockData<V>  {

    String dataType;
    V value;

    public BlockData(String dataType, V value) {
        this.dataType = dataType;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public String getDataType() {
        return dataType;
    }
}
