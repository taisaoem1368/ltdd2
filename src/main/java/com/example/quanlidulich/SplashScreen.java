package com.example.quanlidulich;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backgroup_animation);
        logo = (ImageView) findViewById(R.id.logo);
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.ab);
        logo.startAnimation(aniSlide);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        }, 1000);
    }
}
