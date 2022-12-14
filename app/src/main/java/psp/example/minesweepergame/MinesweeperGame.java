package psp.example.minesweepergame;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame {

    // ---------------------------------- VARIABLES
    private static MineGrid mineGrid;
    private static boolean clearMode;       // Verify if the game is clear mode or not
    private boolean isGameOver;             // When we press a bomb
    private boolean timeExpired;            // Time to complete the game
    private static int flagCount;           // Flag count
    private int numberOfBombs;              // Number of bombs


    // ---------------------------------- CONSTRUCTOR
    public MinesweeperGame(int size, int numberOfBombs){
        this.mineGrid = new MineGrid(size);
        this.mineGrid.generateGrid(numberOfBombs);
        this.clearMode = true;
        this.isGameOver = false;
        this.timeExpired = false;
        this.flagCount = 0;
        this.numberOfBombs = numberOfBombs;
    }

    // ---------------------------------- GETTER / SETTERS
    // mineGrid getter
    public static MineGrid getMineGrid() {
        return mineGrid;
    }

    // isGameOver getter
    public boolean getIsGameOver() {
        return isGameOver;
    }

    // flagCount getter
    public int getFlagCount() {
        return flagCount;
    }

    // numberOfBombs
    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    // ---------------------------------- METHODS
    // Method to handle the click
    public void handleCellClick(Cell cell) {
        // if same is not over, is not won and time is not expired
        if (!isGameOver && !isGameWon() && !timeExpired){
            if (clearMode) {
                clear(cell);    // Clear cell
            }
        }
    }

    // Method to handle the long click
    public void handleCellLongClick(Cell cell) {
        // if same is not over, is not won and time is not expired
        if (!isGameOver && !isGameWon() && !timeExpired){
            flag(cell); // Flag cell
        }
    }

    // Method to clear the cell
    // This method check
    public void clear(Cell cell){
        int index = getMineGrid().getCells().indexOf(cell);
        getMineGrid().getCells().get(index).setIsRevealed(true);

        //If the cell is blank, create 2 list, 1 for cells to clear, other to check adjacent
        if (cell.getValue() == Cell.BLANK) {
            List<Cell> toClear = new ArrayList<>();
            List<Cell> toCheckAdjacents = new ArrayList<>();

            toCheckAdjacents.add(cell); // Add the cell

            // While the check adjacent size > 0
            while (toCheckAdjacents.size() > 0) {
                Cell c = toCheckAdjacents.get(0);
                int cellIndex = getMineGrid().getCells().indexOf(c);
                int[] cellPos = getMineGrid().toXY(cellIndex);
                for (Cell adjacent : getMineGrid().adjacentCells(cellPos[0], cellPos[1])) {
                    if (adjacent.getValue() == Cell.BLANK) {
                        if (!toClear.contains(adjacent)) {
                            if (!toCheckAdjacents.contains(adjacent)) {
                                toCheckAdjacents.add(adjacent);
                            }
                        }
                    } else {
                        if (!toClear.contains(adjacent)) {
                            toClear.add(adjacent);
                        }
                    }
                }
                toCheckAdjacents.remove(c);
                toClear.add(c);
            }

            // Change isRevealed value
            for (Cell c : toClear) {
                c.setIsRevealed(true);
            }
        } else if (cell.getValue() == Cell.BOMB){
            isGameOver = true;
        }
    }

    // Method for game won
    public boolean isGameWon() {
        int numbersUnrevealed = 0;
        for (Cell c: getMineGrid().getCells()) {
            // If the cell is not a bomb, is not blank and is not revealed
            if (c.getValue() != Cell.BOMB && c.getValue() != Cell.BLANK && !c.getIsRevealed()) {
                numbersUnrevealed++; // +1 numbersUnrevealed
            }
        }
        if (numbersUnrevealed == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Method to count the cells flagged
    public static void flag(Cell cell){
        if(!cell.getIsRevealed()){  // If the cell is not revealed
            cell.setIsFlagged(!cell.getIsFlagged());    // Flagg it
            int count = 0;
            for (Cell c: getMineGrid().getCells()){
                if (c.getIsFlagged()){
                    count++;    // For each cell flagged, count +1
                }
            }
            flagCount = count;  // Set flag count number
        }
    }

    // Out of time method
    public void outOfTime(){
        timeExpired = true;
    }
}
