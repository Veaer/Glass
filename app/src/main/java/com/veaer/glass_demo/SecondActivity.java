/*
 * Copyright (C) 2015 Veaer <veaer.king@gmail.com>
 *
 * This file is part of Veaer's project
 *
 * You should have received a copy of the GNU General Public License
 * along with this project.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.veaer.glass_demo;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.veaer.glass.Glass;

/**
 * Created by Veaer on 15/11/5.
 */
public class SecondActivity extends AppCompatActivity {
    ImageView imageView;
    FloatingActionButton fab;
    AppBarLayout appBar;
    Glass mGlass;
    int label = R.mipmap.a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageView = (ImageView)findViewById(R.id.image);
        appBar = (AppBarLayout)findViewById(R.id.app_bar);
        fab = (FloatingActionButton)findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (label) {
                    case R.mipmap.a:
                        label = R.mipmap.b;
                        break;
                    case R.mipmap.b:
                        label = R.mipmap.c;
                        break;
                    case R.mipmap.c:
                        label = R.mipmap.a;
                        break;
                }
                changeImage();
            }
        });
        mGlass = Glass.Builder.newInstance()
                .statusBarWithLower(getWindow(), App.mContext, ContextCompat.getColor(App.mContext, R.color.colorPrimary))
                .background(appBar)
                .build();
        changeImage();
    }

    public void changeImage() {
        imageView.setImageResource(label);
        mGlass.setPaletteBmp(BitmapFactory.decodeResource(getResources(), label), Glass.paletteType.MUTED);
    }

}
