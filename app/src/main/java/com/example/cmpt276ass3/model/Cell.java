package com.example.cmpt276ass3.model;

public class Cell {
    private boolean isMine;
    private int numberOfNearbyMines;

    public Cell(boolean isMine) {
        this.isMine = isMine;
        this.numberOfNearbyMines = -1;
    }

    public void setNumberOfNearbyMines(int numberOfNearbyMines) {
        this.numberOfNearbyMines = numberOfNearbyMines;
    }

    public boolean isMine() {
        return isMine;
    }

    public void cleanMine() {
        isMine = false;
    }

    public int getNumberOfNearbyMines() {
        return numberOfNearbyMines;
    }
}
