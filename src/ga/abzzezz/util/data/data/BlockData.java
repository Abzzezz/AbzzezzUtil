/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;

public class BlockData<E>  {

    String dataType;
    E value;

    public BlockData(String dataType, E value) {
        this.dataType = dataType;
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public String getDataType() {
        return dataType;
    }
}
