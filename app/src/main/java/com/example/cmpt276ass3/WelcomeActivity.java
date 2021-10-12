package com.example.cmpt276ass3;

import android.content.Intent;
import android.os.Bundle;

import com.example.cmpt276ass3.databinding.ActivityWelcomeBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        startGame();

    }


    public void startGame() {
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = GameActivity.newIntent(WelcomeActivity.this);
                startActivity(intent);
            }
        });
    }




}


