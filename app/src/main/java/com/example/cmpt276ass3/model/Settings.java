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
    private int numberOfCats;
    private int numberOfRows;
    private int numberOfColumns;
    private String dimension;

    private Settings(Context context) {
        this.context = context;
        updateCatCount();
        updateDimensions();
    }

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
                this.numberOfRows = 4;
                this.numberOfColumns = 6;
                break;
            case("5 x 10") :
                this.numberOfRows = 5;
                this.numberOfColumns = 10;
                break;
            case("6 x 15") :
                this.numberOfRows = 6;
                this.numberOfColumns = 15;
                break;
        }
    }

    public void updateCatCount() {
        this.numberOfCats = OptionActivity.getCatCountChoice(context);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfCats() {
        return numberOfCats;
    }


}
