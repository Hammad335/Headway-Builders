package com.codewithhamad.headwaybuilders;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.codewithhamad.headwaybuilders.analyst.AnalystActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {


    private ImageView logo;
    private Animation fadeIn;

    private static int SPLASHSCREEN=1300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // init views
        logo = (ImageView) findViewById(R.id.imageView2);

        // setting anim to logo
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein_splash_activity);
        logo.startAnimation(fadeIn);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent = new Intent(SplashActivity.this, AnalystActivity.class);
               startActivity(intent);
               finish();
            }
        },SPLASHSCREEN);

    }

}
