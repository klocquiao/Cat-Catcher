package com.example.cmpt276ass3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView appTitle;
    private ImageView runningCat;
    private ImageView runningCat2;
    private ImageView runningCat3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        appTitle = (TextView) findViewById(R.id.txtAppName);
        runningCat = (ImageView) findViewById(R.id.welcomeImage);
        initSkip();
        playAnimation();

    }

    //Referenced: https://www.youtube.com/watch?v=xd3VtBAqtRo
    private void playAnimation() {
        Animation titleAnimation = AnimationUtils.loadAnimation(this, R.anim.title_anim);
        appTitle.startAnimation(titleAnimation);
        Animation catAnimation = AnimationUtils.loadAnimation(this, R.anim.running_cat_anim);
        runningCat.startAnimation(catAnimation);
    }

    private void initSkip() {
        Button startButton = (Button) findViewById(R.id.skipButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainMenuActivity.newIntent(WelcomeActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }
}