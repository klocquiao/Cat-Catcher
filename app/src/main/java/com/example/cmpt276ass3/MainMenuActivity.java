/**
 * The main menu presents the users with 3 different buttons.
 * The first one ("Start Game!") starts the Cat Catcher game
 * The second one ("Options") presents the user with a menu where they can predetermine certain settings for the game
 * The third presents the user with a tutorial and details of the author. Also citations.
 */

package com.example.cmpt276ass3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.cmpt276ass3.model.Settings;

public class MainMenuActivity extends AppCompatActivity {
    private Settings gameSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        gameSettings = Settings.getInstance(this);

        //Initialize main menu buttons
        startGame();
        startOption();
        startHelp();
    }

    public void startGame() {
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = GameActivity.newIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    public void startOption() {
        Button startButton = findViewById(R.id.optionButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = OptionActivity.newIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    public void startHelp() {
        Button startButton = findViewById(R.id.helpButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = HelpActivity.newIntent(MainMenuActivity.this);
                startActivity(intent);
            }
        });
    }

    public static Intent newIntent(Context c) {
        Intent intent = new Intent(c, MainMenuActivity.class);
        return intent;
    }


}


