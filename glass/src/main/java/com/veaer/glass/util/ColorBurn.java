package com.veaer.glass.util;

import android.graphics.Color;
import android.support.v7.graphics.Palette;

/**
 * Created by Veaer on 15/12/10.
 */
public class ColorBurn {

    public static int colorBurn(Palette.Swatch swatch) {
        if(null == swatch) {
            return Color.parseColor("#3F51B5");
        } else {
            int rgb = swatch.getRgb();
            int alpha = rgb >> 24;
            int red = rgb >> 16 & 0xFF;
            int green = rgb >> 8 & 0xFF;
            int blue = rgb & 0xFF;
            red = (int) Math.floor(red * (1 - 0.1));
            green = (int) Math.floor(green * (1 - 0.1));
            blue = (int) Math.floor(blue * (1 - 0.1));
            return Color.argb(alpha, red, green, blue);
        }
    }
}
