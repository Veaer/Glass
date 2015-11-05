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

import android.support.annotation.ColorInt;
import android.view.View;

/**
 * Created by Veaer on 15/11/3.
 */
public class BackgroundSetter extends Setter {
    private final View view;

    public BackgroundSetter(View view) {
        this.view = view;
    }

    @Override
    protected void onSetColor(@ColorInt int color) {
        view.setBackgroundColor(color);
    }
}
