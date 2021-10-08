package com.example.cmpt276ass3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.cmpt276ass3.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {

    private static final int NUM_ROWS = 3;
    private static final int NUM_COLS = 3;
    private AppBarConfiguration appBarConfiguration;
    private ActivityGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        populateButtons();


    }

    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.gameCells);
        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f));
            table.addView(tableRow);
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = new Button(this);

                button.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f));

                button.setText("");
                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cellButtonClicked(button);
                    }
                });

                tableRow.addView(button);
            }

        }
    }

    private void cellButtonClicked(Button bt) {
        Toast.makeText(this, "TEMP", Toast.LENGTH_LONG).show();
        bt.setText("X");
    }

    public static Intent newIntent(Context c) {
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }
}