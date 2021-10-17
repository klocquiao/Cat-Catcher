/**
 * The welcome menu should be opened immediately when the application is open.
 * It will display the author name, the game title, and also feature a quick animation in which the user may skip.
 * By waiting or clicking the skip button, the user will be brought to the main menu.
 */

package com.example.cmpt276ass3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView appTitle;
    private ImageView runningCat;
    private Button startButton;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        appTitle = (TextView) findViewById(R.id.txtAppName);
        runningCat = (ImageView) findViewById(R.id.welcomeImage);
        startButton = (Button) findViewById(R.id.skipButton);
        initSkip();
        playAnimation();

        //Referenced: https://stackoverflow.com/questions/18103814/close-an-activity-after-10-seconds
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startApp();
            }
        }, 10000);
    }

    private void initSkip() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacksAndMessages(null);

                startApp();
            }
        });
    }

    //Referenced: https://www.youtube.com/watch?v=xd3VtBAqtRo
    private void playAnimation() {
        Animation titleAnimation = AnimationUtils.loadAnimation(this, R.anim.title_anim);
        appTitle.startAnimation(titleAnimation);
        Animation catAnimation = AnimationUtils.loadAnimation(this, R.anim.running_cat_anim);
        runningCat.startAnimation(catAnimation);
    }

    private void startApp() {
        Intent intent = MainMenuActivity.newIntent(WelcomeActivity.this);
        startActivity(intent);
        finish();
    }
}