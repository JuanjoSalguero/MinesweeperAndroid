package psp.example.minesweepergame;

public class Cell {
    // ---------------------------------- CONSTANTS
    public static final int BOMB = -1;
    public static final int BLANK = 0;

    // ---------------------------------- VARIABLES
    private int value;              // Ech cell value
    protected boolean isRevealed;     // Cell revealed or not
    private boolean isFlagged;      // Cell flagged or not

    // ---------------------------------- CONSTRUCTOR
    public Cell(int value){
        this.value = value;
        this.isRevealed = false;
        this.isFlagged = false;
    }
    // ---------------------------------- GETTER / SETTERS

    // Value getter
    public int getValue() {return value; }

    // isRevealed getter / setter
    public boolean getIsRevealed() {return isRevealed; }

    public void setIsRevealed(boolean isRevealed) {this.isRevealed = isRevealed; }

    // isFlagged getter / setter
    public boolean getIsFlagged() {return isFlagged; }

    public void setIsFlagged(boolean isFlagged) {this.isFlagged = isFlagged; }
}
