package com.veaer.glass.viewpager;

import android.support.v4.view.ViewPager;

import com.veaer.glass.trigger.Trigger;

/**
 * Created by Veaer on 15/11/18.
 */
public class PagerTrigger extends Trigger implements ViewPager.OnPageChangeListener {
    private ColorProvider colorProvider;
    private int startPosition, endPosition, maxLimit;

    public static Trigger addTrigger(ViewPager viewPager, ColorProvider colorProvider) {
        PagerTrigger viewPagerTrigger = new PagerTrigger(colorProvider);
        viewPager.addOnPageChangeListener(viewPagerTrigger);
        viewPagerTrigger.onPageSelected(0);
        return viewPagerTrigger;
    }

    PagerTrigger(ColorProvider colorProvider) {
        this.colorProvider = colorProvider;
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

    private boolean isScrollingRight(int position) {
        return position == startPosition;
    }

    private void initColorGenerator() {
        ColorGenerator.init(startPosition, endPosition, colorProvider);
    }

}
