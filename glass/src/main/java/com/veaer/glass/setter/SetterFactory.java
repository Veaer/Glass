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

import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Veaer on 15/11/3.
 */
public class SetterFactory {
    public static Setter getBackgroundSetter(View view) {
        if(FabSetter.isFab(view)) {
            return new FabSetter(view);
        } else {
            return new BackgroundSetter(view);
        }
    }

    public static Setter getTextSetter(TextView view) {
        return new TextSetter(view);
    }

    public static Setter getSystemSetter(Window window) {
        return new SystemSetter(window);
    }
}
