package com.example.cmpt276ass3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    public static final Boolean MINE = true;
    public static final Boolean NO_MINE = false;
    private static int numberOfRows;
    private static int numberOfColumns;
    private static int numberOfMines;
    private int minesFound;
    private int scansPerformed;

    private Cell field[][];
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
        field = new Cell[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                Cell newCell = new Cell(shuffler.remove(0));
                field[i][j] = newCell;
            }
        }

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                scan(i, j);
            }
        }
    }

    public boolean reveal(int row, int column, boolean isScan) {
        boolean cellValue = field[row][column].isMine();

        if (cellValue == MINE) {
            if(!isScan) {
                minesFound++;
            }
            return MINE;
        }
        if (!isScan) {
            scansPerformed++;
        }
        return NO_MINE;
    }
    public void scan(int row, int column) {
        int totalNearbyMines = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            if (reveal(row, i, true) == MINE) {
                totalNearbyMines++;
            }
        }
        for (int i = 0; i < numberOfRows; i++) {
            if (reveal(i, column, true) == MINE) {
                totalNearbyMines++;
            }
        }
        getCell(row, column).setNumberOfNearbyMines(totalNearbyMines);
    }

    public Cell getCell(int row, int col) {
        return field[row][col];
    }

    public int getMinesFound() {
        return minesFound;
    }

    public int getScansPerformed() {
        return scansPerformed;
    }

}
