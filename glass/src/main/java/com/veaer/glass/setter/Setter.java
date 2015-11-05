package com.veaer.glass.setter;

import android.support.annotation.ColorInt;

/**
 * Created by Veaer on 15/11/3.
 */
public abstract class Setter {
    @ColorInt private int oldColor = 0;

    public void setColor(@ColorInt int color) {
        if(oldColor != color) {
            oldColor = color;
            onSetColor(color);
        }
    }

    protected abstract void onSetColor(@ColorInt int color);
}
