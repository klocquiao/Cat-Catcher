package com.example.cmpt276ass3.model;

public class Settings {
    private static Settings instance;
    private static int numberOfMines;
    private static int numberOfRows;
    private static int numberOfColumns;

    private Settings() {
        //Default options
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

    public static int getNumberOfRows() {
        return numberOfRows;
    }

    public static void setNumberOfRows(int numberOfRows) {
        Settings.numberOfRows = numberOfRows;
    }

    public static int getNumberOfColumns() {
        return numberOfColumns;
    }

    public static void setNumberOfColumns(int numberOfColumns) {
        Settings.numberOfColumns = numberOfColumns;
    }

    public static int getNumberOfMines() {
        return numberOfMines;
    }

    public static void setNumberOfMines(int numberOfMines) {
        Settings.numberOfMines = numberOfMines;
    }

}
