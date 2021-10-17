/**
 * Contains the settings for the game. It is a singleton, meaning that these settings are global
 * allowing for easy configuration when a game is initialized. It  contains information as to the
 * dimensions and number of cats.
 */

package com.example.cmpt276ass3.model;

import android.content.Context;

import com.example.cmpt276ass3.OptionActivity;

public class Settings {
    private static Settings instance;
    private static Context context;
    private static int numberOfCats;
    private static int numberOfRows;
    private static int numberOfColumns;
    private String dimension;

    private Settings(Context context) {
        this.context = context;
        updateDimensions();
        updateCatCount();
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
    }

    public void updateCatCount() {
        setNumberOfCats(OptionActivity.getCatCountChoice(context));
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    private void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    private void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfCats() {
        return numberOfCats;
    }

    private void setNumberOfCats(int numberOfCats) {
        this.numberOfCats = numberOfCats;
    }

}
