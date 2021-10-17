package com.example.cmpt276ass3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.example.cmpt276ass3.model.Game;
import com.example.cmpt276ass3.model.Settings;

public class GameActivity extends AppCompatActivity {

    private static int numberOfRows;
    private static int numberOfColumns;
    private static int numberOfCats;
    private static int numberOfGamesStarted;

    private static final String CURRENT_GAMES_STARTED = "Current Games Started";
    private static final String PREFS_GAMES_STARTED = "GamesStartedPref";

    private Settings gameSettings;
    private Button cellArray[][];
    private Game newGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameSettings = Settings.getInstance(this);

        numberOfRows = gameSettings.getNumberOfRows();
        numberOfColumns = gameSettings.getNumberOfColumns();
        numberOfCats = gameSettings.getNumberOfCats();

        cellArray = new Button[numberOfRows][numberOfColumns];
        newGame = new Game(numberOfRows, numberOfColumns, numberOfCats);

        updateNumberOfScansText();
        updateNumberOfCatsText();
        populateButtons();

        numberOfGamesStarted = getGamesStartedCount(this);
        updateNumberOfSGamesStartedText();


        numberOfGamesStarted++;
        saveGamesStartedCount(numberOfGamesStarted);

    }

    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.gameCells);
        for (int row = 0; row < numberOfRows; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f));
            table.addView(tableRow);

            for (int col = 0; col < numberOfColumns; col++) {
                final int FINAL_ROW = row;
                final int FINAL_COL = col;
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
                        cellButtonClicked(FINAL_ROW, FINAL_COL);

                    }
                });

                tableRow.addView(button);
                cellArray[row][col] = button;
            }
        }
    }

    private void lockButtonSize() {
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
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

        if (newGame.reveal(row, col, false) == Game.CAT) {
            newGame.getCell(row, col).catchCat();
            updateScannedCells(row, col);
            updateNumberOfCatsText();
            button.setTextColor(ContextCompat.getColor(this, R.color.white));
            scaleImageToCell(button);
            checkWinner();
        }
        else {
            int cellValue = newGame.getCell(row, col).getNumberOfNearbyCats();
            button.setText(Integer.toString(cellValue));
            button.setEnabled(false);
            updateNumberOfScansText();
        }
    }

    private void updateScannedCells(int row, int col) {
        for (int i = 0; i < numberOfColumns; i++) {
            newGame.scan(row, i);
            Button cell = cellArray[row][i];
            String cellText = cell.getText().toString();
            if (!cellText.isEmpty() && !cellText.matches("0") && !newGame.getCell(row, i).isCat()) {
                int cellValue = newGame.getCell(row, i).getNumberOfNearbyCats();
                cell.setText(Integer.toString(cellValue));
            }
        }
        for (int i = 0; i < numberOfRows; i++) {
            newGame.scan(i, col);
            Button cell = cellArray[i][col];
            String cellText = cell.getText().toString();
            if (!cellText.isEmpty() && !cellText.matches("0") && !newGame.getCell(i, col).isCat()) {
                int cellValue = newGame.getCell(i, col).getNumberOfNearbyCats();
                cell.setText(Integer.toString(cellValue));
            }
        }
    }

    private void updateNumberOfCatsText() {
        TextView catText = (TextView) findViewById(R.id.numberOfCats);
        catText.setText(getString(R.string.number_of_cats, newGame.getCatsFound(), numberOfCats));
    }

    private void updateNumberOfScansText() {
        TextView scanText = (TextView) findViewById(R.id.numberOfScans);
        scanText.setText(getString(R.string.number_of_scans, newGame.getScansPerformed()));
    }

    private void updateNumberOfSGamesStartedText() {
        TextView gamesStartedText = (TextView) findViewById(R.id.numberOfGamesStarted);
        gamesStartedText.setText(getString(R.string.number_of_times_played, numberOfGamesStarted));
    }

    private void scaleImageToCell(Button button) {
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.found_cat);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    private void checkWinner() {
        if (newGame.getCatsFound() == numberOfCats) {
            FragmentManager manager = getSupportFragmentManager();
            GameFragment dialog = new GameFragment();
            dialog.show(manager, "WinnerDialog");
            finish();
        }
    }

    private void saveGamesStartedCount(int gamesStartedCount) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_GAMES_STARTED, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(CURRENT_GAMES_STARTED, gamesStartedCount);
        editor.apply();
    }

    public static int getGamesStartedCount(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_GAMES_STARTED, MODE_PRIVATE);
        return prefs.getInt(CURRENT_GAMES_STARTED, 0);
    }


    public static Intent newIntent(Context c) {
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }
}