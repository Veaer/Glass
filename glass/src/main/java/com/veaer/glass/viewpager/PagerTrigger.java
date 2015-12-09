package com.veaer.glass.viewpager;

import android.support.annotation.ColorInt;
import android.support.v4.view.ViewPager;

import com.veaer.glass.setter.Setter;

import java.util.List;

/**
 * Created by Veaer on 15/11/18.
 */
public class PagerTrigger implements ViewPager.OnPageChangeListener {
    private ColorProvider colorProvider;
    private List<Setter> setters;
    private int startPosition, endPosition, maxLimit;

    public static PagerTrigger addTrigger(ViewPager viewPager, ColorProvider colorProvider, List<Setter> setters) {
        PagerTrigger viewPagerTrigger = new PagerTrigger(colorProvider, setters);
        viewPager.addOnPageChangeListener(viewPagerTrigger);
        viewPagerTrigger.onPageSelected(0);
        return viewPagerTrigger;
    }

    PagerTrigger(ColorProvider colorProvider, List<Setter> setters) {
        this.colorProvider = colorProvider;
        this.setters = setters;
        maxLimit = colorProvider.getCount() - 1;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (isScrollingRight(position)) {
            startPosition = position;
            endPosition = Math.min(maxLimit, position + 1);
        } else {
            startPosition = Math.min(maxLimit, position + 1);
            endPosition = position;
        }
        initColorGenerator();
        setColor(ColorGenerator.getColor(position, positionOffset));
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //do nothing
    }

    @Override
    public void onPageSelected(int position) {
        endPosition = position;
        startPosition = position;
        initColorGenerator();
    }

    public void setColor(@ColorInt int newColor) {
        for (Setter setter : setters) {
            setter.setColor(newColor);
        }
    }

    public void destroy() {
        this.setters.clear();
    }

    private boolean isScrollingRight(int position) {
        return position == startPosition;
    }

    private void initColorGenerator() {
        ColorGenerator.init(startPosition, endPosition, colorProvider);
    }

}
