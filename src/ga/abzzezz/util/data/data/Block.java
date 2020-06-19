/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;

import ga.abzzezz.util.stringing.StringUtil;

public class Block<E> {

    /**
     * For future purposes
     */

    //Block bounds
    private final char[] bounding = {'{', '}'};
    //Block, can be inner or complete block
    private final String block;

    /**
     * @param in
     */
    public Block(String in) {
        this.block = in;
    }

    /**
     * Get block bounds
     * {             }
     * 1             2
     *
     * @return
     */
    public int[] getBlock() {
        int begin = block.indexOf(bounding[0]);
        int end = block.indexOf(bounding[1]);
        return new int[]{begin, end + 1};
    }

    /**
     * Get inner block
     *
     * @return
     */
    public String getInnerBlock() {
        int[] block = getBlock();
        return this.block.substring(block[0] + 1, block[1] - 1);
    }

    public String[] getAll() {
        return getInnerBlock().split(StringUtil.splitter);
    }

    public E getValue() {
        return (E) getAll()[2];
    }

    public String getKey() {
        return getAll()[1];
    }

    public String getDataType() {
        return getAll()[0];
    }

    /**
     * Format block with bounds
     *
     * @return
     */
    public String formatBlock() {
        return bounding[0] + block + bounding[1];
    }
}
