package com.example.cmpt276ass3.model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    public static final Boolean MINE = true;
    public static final Boolean NO_MINE = false;
    private static int numberOfRows;
    private static int numberOfColumns;
    private static int numberOfMines;
    private int minesFound;
    private int scansPerformed;

    private boolean field[][];
    private ArrayList<Boolean> shuffler = new ArrayList<Boolean>();


    public Game(int numberOfRows, int numberOfColumns, int numberOfMines) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.numberOfMines = numberOfMines;
        minesFound = 0;
        scansPerformed = 0;

        shuffleFieldValues();
        populateField();
    }

    private void shuffleFieldValues() {
        int minesInField = 0;
        int currentSize = 0;
        while(currentSize < numberOfColumns * numberOfRows) {
            if (minesInField < numberOfMines) {
                shuffler.add(MINE);
                minesInField++;
            }
            else {
                shuffler.add(NO_MINE);
            }
            currentSize++;
        }
        Collections.shuffle(shuffler);
    }


    private void populateField() {
        field = new boolean[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                field[i][j] = shuffler.remove(0);
            }
        }
    }

    public boolean reveal(int row, int column) {
        boolean scannedValue = field[row][column];
        scansPerformed++;

        if (scannedValue == MINE) {
            minesFound++;
            return MINE;
        }
        return NO_MINE;

    }
    public int scan(int row, int column) {
        int totalNearbyMines = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            if (reveal(row, i) == MINE) {
                totalNearbyMines++;
            }
        }
        for (int i = 0; i < numberOfRows; i++) {
            if (reveal(i, column) == MINE) {
                totalNearbyMines++;
            }
        }
        return totalNearbyMines;
    }

}
