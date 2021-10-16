package com.example.cmpt276ass3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.cmpt276ass3.databinding.ActivityGameBinding;
import com.example.cmpt276ass3.model.Game;
import com.example.cmpt276ass3.model.Settings;

public class GameActivity extends AppCompatActivity {

    private static int NUM_ROWS;
    private static int NUM_COLS;
    private static int NUM_MINES;
    private Settings gameSettings;
    private Button cellArray[][];
    private Game newGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameSettings = Settings.getInstance(this);

        NUM_ROWS = gameSettings.getNumberOfRows();
        NUM_COLS = gameSettings.getNumberOfColumns();
        NUM_MINES = gameSettings.getNumberOfMines();

        cellArray = new Button[NUM_ROWS][NUM_COLS];
        newGame = new Game(NUM_ROWS, NUM_COLS, NUM_MINES);

        updateNumberOfScansText();
        updateNumberOfMinesText();
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
                final int FINAL_ROW = row;
                final int FINAL_COL = col;
                Button button = new Button(this);

                button.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f));

                button.setText("x");
                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cellButtonClicked(FINAL_ROW, FINAL_COL);

                    }
                });

                tableRow.addView(button);
                cellArray[row][col] = button;
            }

        }
    }

    private void lockButtonSize() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = cellArray[row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void cellButtonClicked(int row, int col) {
        Button button = cellArray[row][col];

        lockButtonSize();

        if (newGame.reveal(row, col, false) == Game.MINE) {
            button.setText("Found!");
            newGame.getCell(row, col).cleanMine();
            updateScannedCells(row, col);
            updateNumberOfMinesText();
            checkWinner();
        }
        else {
            int cellValue = newGame.getCell(row, col).getNumberOfNearbyMines();
            button.setText(Integer.toString(cellValue));
            button.setEnabled(false);
            updateNumberOfScansText();
        }
    }

    private void updateScannedCells(int row, int col) {
        for (int i = 0; i < NUM_COLS; i++) {
            newGame.scan(row, i);
            Button cell = cellArray[row][i];
            String cellText = cell.getText().toString();
            if (!cellText.matches("x") && !cellText.matches("0") && !cellText.matches("Found!")) {
                int cellValue = newGame.getCell(row, i).getNumberOfNearbyMines();
                cell.setText(Integer.toString(cellValue));
            }
        }
        for (int i = 0; i < NUM_ROWS; i++) {
            newGame.scan(i, col);
            Button cell = cellArray[i][col];
            String cellText = cell.getText().toString();
            if (!cellText.matches("x") && !cellText.matches("0") && !cellText.matches("Found!")) {
                int cellValue = newGame.getCell(i, col).getNumberOfNearbyMines();
                cell.setText(Integer.toString(cellValue));
            }
        }
    }

    private void updateNumberOfMinesText() {
        TextView mineText = (TextView) findViewById(R.id.numberOfMines);
        mineText.setText("Found " + newGame.getMinesFound() + " of " + NUM_MINES + " mines");
    }
    private void updateNumberOfScansText() {
        TextView mineText = (TextView) findViewById(R.id.numberOfScans);
        mineText.setText("# Scans used: " + newGame.getScansPerformed());
    }

    private void scaleImageToCell(Button button) {
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture_of_cat);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    private void checkWinner() {
        if (newGame.getMinesFound() == NUM_MINES) {
            FragmentManager manager = getSupportFragmentManager();
            GameFragment dialog = new GameFragment();
            dialog.show(manager, "WinnerDialog");
        }
    }

    public static Intent newIntent(Context c) {
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }
}