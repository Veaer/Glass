package com.veaer.glass.trigger;

import android.support.annotation.ColorInt;

import com.veaer.glass.setter.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veaer on 15/11/3.
 */
public class Trigger {
    private List<Setter> setters = new ArrayList<>();
    @ColorInt private int oldColor = 0;

    public void addSetter(Setter setter) {
        setters.add(setter);
    }

    public void addSetterList(List<Setter> setterList) {
        setters.addAll(setterList);
    }

    public void setColor(@ColorInt int color) {
        if(oldColor != color) {
            oldColor = color;
            onSetColor(color);
        }
    }

    public void onSetColor(@ColorInt int newColor) {
        for (Setter setter : setters) {
            setter.setColor(newColor);
        }
    }
}
