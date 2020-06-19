/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.data.data;

import ga.abzzezz.util.math.MathUtil;
import ga.abzzezz.util.stringing.StringUtil;

/**
 * Used to format blocks
 */
public class BlockFormatter {

    /**
     * @see Block
     */
    private final char[] bounding = {'{', '}'};

    /**
     * Format block so it can be decoded
     *
     * @param o
     * @param key
     * @param val
     * @return
     */
    public String formatBlock(Object o, String key, String val) {
        return new Block((o.getClass().isArray() ? "Array:" + o.getClass().getSimpleName() : o.getClass().getSimpleName()) + StringUtil.splitter + key + StringUtil.splitter + val).formatBlock();
    }

    /**
     * Get size of all blocks in a string. Gets lowest to avoid interference
     *
     * @param string
     * @return
     */
    public int getBlockElements(String string) {
        return MathUtil.getLowest(StringUtil.getTotalCharInString(string, bounding[0]), StringUtil.getTotalCharInString(string, bounding[1]));
    }
}
