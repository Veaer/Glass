/*
 * Copyright (C) 2015 Veaer <veaer.king@gmail.com>
 *
 * This file is part of Veaer's project
 *
 * You should have received a copy of the GNU General Public License
 * along with this project.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.veaer.glass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.veaer.glass.setter.Setter;
import com.veaer.glass.setter.SetterFactory;
import com.veaer.glass.trigger.Trigger;
import com.veaer.glass.util.ColorBurn;
import com.veaer.glass.util.LocalDisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veaer on 15/11/3.
 */
public class Glass extends Setter {
    private final List<Setter> setters;
    private final List<Trigger> triggers;

    private Glass(List<Setter> setters, List<Trigger> triggers) {
        this.setters = setters;
        this.triggers = triggers;
    }

    @Override
    protected void onSetColor(@ColorInt int color) {
        for (Setter setter : setters) {
            setter.setColor(color);
        }
        for (Trigger trigger : triggers) {
            trigger.setColor(color);
        }
    }

    public void setPaletteBmp(Bitmap bitmap) {
        new Palette.Builder(bitmap).generate(listener);
    }

    public void onDestroy() {
        this.setters.clear();
        this.triggers.clear();
    }

    private Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette newPalette) {
            setColor(ColorBurn.colorBurn(newPalette.getVibrantSwatch()));
        }
    };

    public static final class Builder {
        private int defaultColor = Color.parseColor("#3F51B5");
        private boolean changeColor = false;
        private List<Setter> setters;
        private List<Trigger> triggers;


        public static Builder newInstance() {
            return new Builder(new ArrayList<Setter>());
        }

        private Builder(List<Setter> setters) {
            this.setters = setters;
        }

        public Builder add(Setter setter) {
            if(null != setter) {
                setters.add(setter);
            }
            return this;
        }

        public Builder addTrigger(Trigger trigger) {
            triggers.add(trigger);
            return this;
        }

        public Builder background(@NonNull View view) {
            return add(SetterFactory.getBackgroundSetter(view));
        }

        public Builder text(@NonNull TextView view) {
            return add(SetterFactory.getTextSetter(view));
        }

        public Builder defaultColor(@ColorInt int defaultColor) {
            this.defaultColor = defaultColor;
            changeColor = true;
            return this;
        }

        public Builder statusBar(Window window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                Context context = window.getContext().getApplicationContext();
                LocalDisplay.init(context);
                WindowManager.LayoutParams localLayoutParams = window.getAttributes();
                localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
                ViewGroup contentView = (ViewGroup)window.getDecorView().findViewById(android.R.id.content);
                View mStatusBarView = new View(context);
                int height =  LocalDisplay.getStatusHeight(context.getResources());
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height);
                params.gravity = Gravity.TOP;
                mStatusBarView.setLayoutParams(params);
                contentView.addView(mStatusBarView);
                background(mStatusBarView);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Setter windowSetter = SetterFactory.getSystemSetter(window);
                add(windowSetter);
            }
            return this;
        }

        public Glass build() {
            Glass mGlass = new Glass(setters, triggers);
            if(changeColor) {
                mGlass.setColor(defaultColor);
            }
            return mGlass;
        }
    }

}
