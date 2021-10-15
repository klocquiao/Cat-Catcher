package com.example.cmpt276ass3.model;

import android.content.Context;
import android.util.Log;

import com.example.cmpt276ass3.OptionActivity;

public class Settings {
    private static Settings instance;
    private static Context context;
    private static int numberOfMines;
    private static int numberOfRows;
    private static int numberOfColumns;
    private String dimension;

    private Settings(Context context) {
        this.context = context;
        updateDimensions();
        updateMineCount();
    }

    //get context from parameter
    public static Settings getInstance(Context context) {
        if (instance == null) {
            instance = new Settings(context);
        }
        return instance;
    }

    public void updateDimensions() {
        dimension = OptionActivity.getDimensionChoice(context);
        switch(dimension) {
            case("4 x 6") :
                setNumberOfRows(4);
                setNumberOfColumns(6);
                break;
            case("5 x 10") :
                setNumberOfRows(5);
                setNumberOfColumns(10);
                break;
            case("6 x 15") :
                setNumberOfRows(6);
                setNumberOfColumns(15);
                break;
        }

        Log.i("TAG", "Rows: " + numberOfRows);
        Log.i("TAG", "Col: " + numberOfColumns);

    }

    public void updateMineCount() {
        setNumberOfMines(OptionActivity.getMineCountChoice(context));
        Log.i("TAG", "Mine: " + numberOfMines);

    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    private void setNumberOfRows(int numberOfRows) {
        Settings.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    private void setNumberOfColumns(int numberOfColumns) {
        Settings.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    private void setNumberOfMines(int numberOfMines) {
        Settings.numberOfMines = numberOfMines;
    }

}
