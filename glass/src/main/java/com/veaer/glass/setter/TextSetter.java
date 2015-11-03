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
import android.widget.TextView;

/**
 * Created by Veaer on 15/11/3.
 */
public class TextSetter extends Setter {
    private final TextView view;

    public TextSetter(TextView view) {
        this.view = view;
    }

    @Override
    void onSetColor(@ColorInt int color) {
        view.setTextColor(color);
    }
}
