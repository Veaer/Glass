/*
 * Copyright (C) 2015 Veaer <veaer.king@gmail.com>
 *
 * This file is part of Veaer's project
 *
 * You should have received a copy of the GNU General Public License
 * along with this project.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.veaer.glass.setter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Window;

/**
 * Created by Veaer on 15/11/3.
 */
class SystemSetter extends Setter {
    private final Window window;

    public SystemSetter(Window window) {
        this.window = window;
    }

    @Override
    public void onSetColor(@ColorInt int colour) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColour(colour);
            setNavigationBarColour(colour);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColour(@ColorInt int colour) {
        window.setStatusBarColor(colour);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setNavigationBarColour(@ColorInt int colour) {
        window.setNavigationBarColor(colour);
    }
}