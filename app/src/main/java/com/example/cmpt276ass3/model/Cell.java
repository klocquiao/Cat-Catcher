/**
 * A cell contains the information as to the number of cats local to the cell, as well as
 * whether or not a cell contains a cat or not
 */

package com.example.cmpt276ass3.model;

public class Cell {
    private boolean isCat;
    private int numberOfNearbyCats;
    private final static int UNKNOWN = -1;

    public Cell(boolean isCat) {
        this.isCat = isCat;
        this.numberOfNearbyCats = UNKNOWN;
    }

    public void setNumberOfNearbyCats(int numberOfNearbyCats) {
        this.numberOfNearbyCats = numberOfNearbyCats;
    }

    public boolean isCat() {
        return isCat;
    }

    public void catchCat() {
        this.isCat = false;
    }

    public int getNumberOfNearbyCats() {
        return numberOfNearbyCats;
    }


}
