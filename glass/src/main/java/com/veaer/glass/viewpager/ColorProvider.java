package com.veaer.glass.viewpager;

import android.support.annotation.ColorInt;

/**
 * Created by Veaer on 15/11/18.
 */
public interface ColorProvider {
    @ColorInt
    int getColor(int position);
    int getCount();
}
