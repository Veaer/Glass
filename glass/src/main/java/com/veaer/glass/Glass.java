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
import android.support.v4.view.ViewPager;
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
import com.veaer.glass.util.LocalDisplay;
import com.veaer.glass.viewpager.ColorProvider;
import com.veaer.glass.viewpager.PagerTrigger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Veaer on 15/11/3.
 */
public class Glass extends Setter {
    private final List<Setter> setters;
    public enum paletteType {VIBRANT, VIBRANT_DARK, VIBRANT_LIGHT, MUTED , MUTED_DARK, MUTED_LIGHT}
    private Glass.paletteType mPaletteType = paletteType.MUTED_DARK;

    private Glass(List<Setter> setters) {
        this.setters = setters;
    }
    private Glass(List<Setter> setters, ViewPager viewPager, ColorProvider colorProvider) {
        this.setters = setters;
        PagerTrigger.addTrigger(viewPager, colorProvider, this);
    }

    @Override
    protected void onSetColor(@ColorInt int color) {
        for (Setter setter : setters) {
            setter.setColor(color);
        }
    }

    public void setPaletteBmp(Bitmap bitmap) {
        new Palette.Builder(bitmap).generate(listener);
    }

    public void setPaletteBmp(Bitmap bitmap, Glass.paletteType type) {
        this.mPaletteType = type;
        setPaletteBmp(bitmap);
    }

    public void onDestory() {
        this.setters.clear();
    }

    private Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette newPalette) {
            switch (mPaletteType) {
                case VIBRANT :
                    setColor(newPalette.getVibrantColor(0));
                    break;
                case VIBRANT_DARK :
                    setColor(newPalette.getDarkVibrantColor(0));
                    break;
                case VIBRANT_LIGHT :
                    setColor(newPalette.getLightVibrantColor(0));
                    break;
                case MUTED :
                    setColor(newPalette.getMutedColor(0));
                    break;
                case MUTED_DARK :
                    setColor(newPalette.getDarkMutedColor(0));
                    break;
                case MUTED_LIGHT :
                    setColor(newPalette.getLightMutedColor(0));
                    break;
            }
        }
    };

    public static final class Builder {
        private int defaultColor = Color.parseColor("#3F51B5");
        private boolean changeColor = false;
        private List<Setter> setters;
        private ViewPager viewPager;
        private ColorProvider colorProvider;


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

        public Builder background(@NonNull View view) {
            return add(SetterFactory.getBackgroundSetter(view));
        }

        public Builder text(@NonNull TextView view) {
            return add(SetterFactory.getTextSetter(view));
        }

        public Builder statusBar(Window window) {
            return statusBarWithLower(window, null);
        }

        public Builder setViewPager(ViewPager viewPager, ColorProvider colorProvider) {
            this.viewPager = viewPager;
            this.colorProvider = colorProvider;
            return this;
        }

        public Builder defaultColor(@ColorInt int defaultColor) {
            this.defaultColor = defaultColor;
            changeColor = true;
            return this;
        }

        public Builder statusBarWithLower(Window window, Context context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
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
            Glass mGlass;
            if(viewPager != null && colorProvider != null) {
                mGlass = new Glass(setters, viewPager, colorProvider);
            } else {
                mGlass = new Glass(setters);
            }
            if(changeColor) {
                mGlass.setColor(defaultColor);
            }
            return mGlass;
        }
    }

}
