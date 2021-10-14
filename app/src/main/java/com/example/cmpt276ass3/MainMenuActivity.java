package com.example.cmpt276ass3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        startGame();
        startOption();
        startHelp();

    }


    public void startGame() {
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = GameActivity.newIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    public void startOption() {
        Button startButton = (Button) findViewById(R.id.optionButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = OptionActivity.newIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    public void startHelp() {
        Button startButton = (Button) findViewById(R.id.helpButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = HelpActivity.newIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }




}

