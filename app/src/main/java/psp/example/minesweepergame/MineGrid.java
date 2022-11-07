package psp.example.minesweepergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineGrid {

    // ---------------------------------- VARIABLES
    private List<Cell> cells;   // All cells
    private int size;           // Number of tiles in the rows and columns

    // ---------------------------------- CONSTRUCTOR
    public MineGrid(int size) {
        this.size = size;
        this.cells = new ArrayList<>();
        for (int i = 0; i < size * size; i++) {
            cells.add(new Cell(Cell.BLANK));
        }
    }

    // ---------------------------------- GETTERS / SETTERS
    public List<Cell> getCells() {
        return cells;
    }

    // ---------------------------------- METHODS

    // Method that generate the grid with a number of bombs
    public void generateGrid(int numberBombs) {
        int bombsPlaced = 0;

        // Check that the number of bombs placed is less than the number of bombs we need to place
        while (bombsPlaced < numberBombs) {
            // Random coordinates between 0 and size given
            int x = new Random().nextInt(size);
            int y = new Random().nextInt(size);

            // Obtain the index
            int index = toIndex(x, y);
            // Check that the cell index given is blank
            if (cells.get(index).getValue() == Cell.BLANK){
                // If it is blank, a bomb is placed
                cells.set(index, new Cell(Cell.BOMB));
                bombsPlaced++; // +1 count to our bombsPlaced
            }
        }

        // Once the bomb are place, we need to calculate the numbers to display in that grid near the bombs
        // X direction
        for (int x = 0; x < size; x++) {
            // Y direction
            for (int y = 0; y < size; y++) {
                // For each cell we calculate that the value is not a bomb
                if (cellAt(x, y).getValue() != Cell.BOMB) { // If it is not a bomb
                    List<Cell> adjacentCells = adjacentCells(x, y);
                    int countBombs = 0;
                    for (Cell cell: adjacentCells) {    // Check if in the adjacent cells there are bombs
                        if (cell.getValue() == Cell.BOMB) {
                            countBombs++;   // Bombs +1 if it find one
                        }
                    }
                    if (countBombs > 0) {   // If there are mor than 0 bombs
                        cells.set(x + (y*size), new Cell(countBombs));  // Update de value with
                                                                        // the number of bombs touching the cell
                    }
                }
            }
        }
    }

    // Method that converts x and y coordinates into an index in our list
    public int toIndex(int x, int y) {
        return x + (y*size);
    }

    // Method that converts an index into x and y coordinates
    public int[] toXY(int index) {
        int y = index / size;
        int x = index - (y*size);
        return new int[]{x, y};
    }

    // Method that checks if the value coordinate given is inside the grid size
    public Cell cellAt(int x, int y){
        if (x < 0 || x >= size || y < 0 || y >= size){
            return null;
        }
        return cells.get(toIndex(x, y));
    }

    // Method to check tile adjacent cells
    public List<Cell> adjacentCells(int x, int y) {
        List<Cell> adjacentCells = new ArrayList<>();

        List<Cell> cellsList = new ArrayList<>();

        // Circling around the one tile
        cellsList.add(cellAt(x-1, y));
        cellsList.add(cellAt(x+1, y));
        cellsList.add(cellAt(x-1, y-1));
        cellsList.add(cellAt(x, y-1));
        cellsList.add(cellAt(x+1, y-1));
        cellsList.add(cellAt(x-1, y+1));
        cellsList.add(cellAt(x, y+1));
        cellsList.add(cellAt(x+1, y+1));

        // Check all the cells, if it it not null, add it to adjacentCells arraylist
        for (Cell cell: cellsList) {
            if (cell != null) {
                adjacentCells.add(cell);
            }
        }

        return adjacentCells;
    }

    // Method to reeal all boms
    public void revealAllBombs(){
        for (Cell cell: cells){
            if (cell.getValue() == Cell.BOMB){
                cell.setIsRevealed(true);
            }
        }
    }
}
