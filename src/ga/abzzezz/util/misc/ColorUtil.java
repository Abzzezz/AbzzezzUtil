/*
 * @author Roman P.
 * Abzzezz Util is used to automate easy tasks.
 *
 */

package ga.abzzezz.util.misc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Util for color stuff
 */
public class ColorUtil {

    /**
     * Convert hex to RGB
     *
     * @param hex
     * @return
     */
    public static int convertToRGB(int hex) {
        return new Color(hex).getRGB();
    }

    /**
     * Make color lighter by certain amount
     *
     * @param color
     * @param amount
     * @return
     */
    public static Color lighter(Color color, int amount) {
        for (int i = 0; i < amount; i++) color = color.brighter();
        return color;
    }

    /**
     * Make color darker by certain amount
     *
     * @param color
     * @param amount
     * @return
     */
    public static Color darker(Color color, int amount) {
        for (int i = 0; i < amount; i++) color = color.darker();
        return color;
    }

    /**
     * Invert color
     *
     * @param color
     * @return
     */
    public static int invertColor(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue(), 255 - color.getAlpha()).getRGB();
    }

    /**
     * Add Alpha to color
     *
     * @param color
     * @param alpha
     * @return
     */
    public static Color addAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    /**
     * convert color to integer
     *
     * @param color
     * @return
     */
    public static int convertColorToInt(Color color) {
        return color.getRGB();
    }

    /**
     * @param color
     * @return
     */
    public static float convertColorToFloat(Color color) {
        return color.getRGB();
    }

    /**
     * convert to hexadecimal string
     *
     * @param color
     * @return
     */
    public static String convertColorToHexDecimal(Color color) {
        return "0x" + Integer.toHexString(color.getRGB());
    }

    /**
     * Actually not useful
     */
    public static void invertPictureColor() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        File picture = chooser.getSelectedFile();

        try {
            BufferedImage bufferedImage = ImageIO.read(picture);
            for (int i = 0; i < bufferedImage.getHeight(); i++) {
                for (int i1 = 0; i1 < bufferedImage.getWidth(); i1++) {
                    bufferedImage.setRGB(i1, i, ColorUtil.invertColor(new Color(bufferedImage.getRGB(i1, i))));
                }
            }
            ImageIO.write(bufferedImage, "png", new File(System.getProperty("user.home"), "out.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
