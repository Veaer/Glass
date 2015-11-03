package com.veaer.glass_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.veaer.glass.Glass;

public class MainActivity extends AppCompatActivity {
    TextView textView, colorView;
    FloatingActionButton fab;
    AppBarLayout appBar;
    int color = Color.BLACK;
    Glass mGlass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.text_view);
        colorView = (TextView)findViewById(R.id.color_view);
        appBar = (AppBarLayout)findViewById(R.id.app_bar);
        fab = (FloatingActionButton)findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (color) {
                    case Color.BLACK:
                        color = Color.BLUE;
                        break;
                    case Color.BLUE:
                        color = Color.RED;
                        break;
                    case Color.RED:
                        color = Color.BLACK;
                        break;
                }
                mGlass.setColor(color);
            }
        });
        mGlass = Glass.Builder.newInstance()
                .statusBarWithLower(getWindow(), App.mContext, ContextCompat.getColor(App.mContext, R.color.colorPrimary))
                .text(textView)
                .background(appBar)
                .background(fab)
                .background(colorView)
                .build();
    }
}
