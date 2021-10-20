/**
 * Contains the game logic. It populates shuffles the field, placing cats in random lcoations.
 * It also contains information as to what happens when a cat is found or if a cat isn't found.
 */

package com.example.cmpt276ass3.model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    public static final Boolean CAT = true;
    public static final Boolean NO_CAT = false;
    private int numberOfRows;
    private int numberOfColumns;
    private int numberOfCats;
    private int catsFound;
    private int scansPerformed;

    private Cell field[][];
    private ArrayList<Boolean> shuffler = new ArrayList<Boolean>();

    public Game(int numberOfRows, int numberOfColumns, int numberOfCats) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.numberOfCats = numberOfCats;
        this.catsFound = 0;
        this.scansPerformed = 0;

        shuffleFieldValues();
        populateField();
    }

    private void shuffleFieldValues() {
        int catsInField = 0;
        int currentSize = 0;
        while(currentSize < numberOfColumns * numberOfRows) {
            if (catsInField < numberOfCats) {
                shuffler.add(CAT);
                catsInField++;
            }
            else {
                shuffler.add(NO_CAT);
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

    public boolean reveal(int row, int column, boolean isSetup) {
        boolean cellValue = field[row][column].isCat();

        if (cellValue == CAT) {
            if(!isSetup) {
                catsFound++;
            }
            return CAT;
        }
        if (!isSetup) {
            scansPerformed++;
        }
        return NO_CAT;
    }

    public void scan(int row, int column) {
        int totalNearbyCats = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            if (reveal(row, i, true) == CAT) {
                totalNearbyCats++;
            }
        }
        for (int i = 0; i < numberOfRows; i++) {
            if (reveal(i, column, true) == CAT) {
                totalNearbyCats++;
            }
        }
        getCell(row, column).setNumberOfNearbyCats(totalNearbyCats);
    }

    public Cell getCell(int row, int col) {
        return field[row][col];
    }

    public int getCatsFound() {
        return catsFound;
    }

    public int getScansPerformed() {
        return scansPerformed;
    }

}
