package com.example.cmpt276ass3.model;

import com.example.cmpt276ass3.OptionActivity;

public class Settings {
    private static Settings instance;
    private static int numberOfMines;
    private static int numberOfRows;
    private static int numberOfColumns;

    private Settings() {
        numberOfRows = 4;
        numberOfColumns = 6;
        numberOfMines = 6;
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        Settings.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        Settings.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        Settings.numberOfMines = numberOfMines;
    }

}
