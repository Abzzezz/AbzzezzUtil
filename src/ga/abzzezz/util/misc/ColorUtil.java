package ga.abzzezz.util.misc;

import java.awt.*;

public class ColorUtil {


    public static int convertToRGB(int hex) {
        return new Color(hex).getRGB();
    }

    public static Color lighter(Color color, int amount) {
        for (int i = 0; i < amount; i++) {
            color = color.brighter();
        }
        return color;
    }

    public static Color darker(Color color, int amount) {
        for (int i = 0; i < amount; i++) {
            color = color.darker();
        }
        return color;
    }

    public static Color addAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static int convertColorToInt(Color color) {
        return color.getRGB();
    }

    public static float convertColorToFloat(Color color) {
        return color.getRGB();
    }

    public static String convertColorToHexDecimal(Color color) {
        return "0x" + Integer.toHexString(color.getRGB());
    }
}
