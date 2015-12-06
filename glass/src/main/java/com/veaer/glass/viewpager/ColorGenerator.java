package com.veaer.glass.viewpager;

import android.animation.ArgbEvaluator;

/**
 * Created by Veaer on 15/11/19.
 */
public class ColorGenerator {
    private static int startPosition;
    private static int startColor;
    private static int endPosition;
    private static int endColor;
    private static int range;

    public static void init(int start, int end, ColorProvider colorProvider) {
        startPosition = Math.min(start, end);
        endPosition = Math.max(start, end);
        startColor = colorProvider.getColor(startPosition);
        endColor = colorProvider.getColor(endPosition);
        range = endPosition - startPosition;
    }

    public static int getColor(int position, float positionOffset) {
        float offset;
        if (range > 1) {
            offset = ((float) (position - startPosition) / range) + (positionOffset / (float) range);
        } else if (position == endPosition && positionOffset == 0f) {
            offset = 1f;
        } else {
            offset = positionOffset;
        }
        return (Integer)(new ArgbEvaluator().evaluate(offset, startColor, endColor));
    }
}
