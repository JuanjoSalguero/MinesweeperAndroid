package psp.example.minesweepergame;

public class MinesweeperGame {

    // ---------------------------------- VARIABLES
    private MineGrid mineGrid;

    // ---------------------------------- CONSTRUCTOR
    public MinesweeperGame(int size, int numberOfBombs){
        this.mineGrid = new MineGrid(size);
        this.mineGrid.generateGrid(numberOfBombs);
    }

    // ---------------------------------- GETTER / SETTERS

    // mineGrid getter
    public MineGrid getMineGrid() {
        return mineGrid;
    }
}
