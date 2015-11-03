/*
 * Copyright (C) 2015 Veaer <veaer.king@gmail.com>
 *
 * This file is part of Veaer's project
 *
 * You should have received a copy of the GNU General Public License
 * along with this project.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.veaer.glass.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Veaer on 15/11/3.
 */
public class LocalDisplay {
    public static int SCREEN_WIDTH_PIXELS;
    public static int SCREEN_HEIGHT_PIXELS;
    public static float SCREEN_DENSITY;
    public static int SCREEN_WIDTH_DP;
    public static int SCREEN_HEIGHT_DP;
    private static boolean sInitialed;

    public LocalDisplay() {}

    public static void init(Context context) {
        if(!sInitialed && context != null) {
            sInitialed = true;
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager)context.getSystemService("window");
            wm.getDefaultDisplay().getMetrics(dm);
            SCREEN_WIDTH_PIXELS = dm.widthPixels;
            SCREEN_HEIGHT_PIXELS = dm.heightPixels;
            SCREEN_DENSITY = dm.density;
            SCREEN_WIDTH_DP = (int)((float)SCREEN_WIDTH_PIXELS / dm.density);
            SCREEN_HEIGHT_DP = (int)((float)SCREEN_HEIGHT_PIXELS / dm.density);
        }
    }

    public static int dp2px(float dp) {
        float scale = SCREEN_DENSITY;
        return (int)(dp * scale + 0.5F);
    }

    public static int designedDP2px(float designedDp) {
        if(SCREEN_WIDTH_DP != 320) {
            designedDp = designedDp * (float)SCREEN_WIDTH_DP / 320.0F;
        }

        return dp2px(designedDp);
    }

    public static void setPadding(View view, float left, float top, float right, float bottom) {
        view.setPadding(designedDP2px(left), dp2px(top), designedDP2px(right), dp2px(bottom));
    }

    public static void addLeftPadding(View view, float left) {
        view.setPadding(view.getPaddingLeft() + LocalDisplay.dp2px(left), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void addRightPadding(View view, float right) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight() + LocalDisplay.dp2px(right), view.getPaddingBottom());
    }
    public static void addTopPadding(View view, float top) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + LocalDisplay.dp2px(top), view.getPaddingRight(), view.getPaddingBottom());
    }
    public static void addBottomPadding(View view, float bottom) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom() + LocalDisplay.dp2px(bottom));
    }
}