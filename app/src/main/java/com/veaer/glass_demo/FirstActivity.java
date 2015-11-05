package com.veaer.glass_demo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.veaer.glass.Glass;

public class FirstActivity extends AppCompatActivity {
    TextView textView, colorView, secondView;
    FloatingActionButton fab;
    AppBarLayout appBar;
    int color = Color.BLACK;
    Glass mGlass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textView = (TextView)findViewById(R.id.text_view);
        colorView = (TextView)findViewById(R.id.color_view);
        secondView = (TextView)findViewById(R.id.second_view);
        appBar = (AppBarLayout)findViewById(R.id.app_bar);
        fab = (FloatingActionButton)findViewById(R.id.fab_btn);
        secondView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.mContext, SecondActivity.class);
                startActivity(intent);
            }
        });
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

        mGlass.setPaletteBmp(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), Glass.paletteType.MUTED_DARK);
    }
}
