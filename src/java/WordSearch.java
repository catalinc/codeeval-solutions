import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordSearch {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                if (search(word)) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
            }
        }
    }

    private static boolean search(String word) {
        int nRows = GRID.length;
        int nCols = GRID[0].length;
        unmarkAll();
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                Cell cell = GRID[r][c];
                if (search(cell, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean search(Cell cell, String word) {
        if (cell.getCh() == word.charAt(0)) {
            cell.setMarked(true);
            if (word.length() == 1) {
                return true;
            } else {
                String restOfWord = word.substring(1);
                for (Cell neighbour : neighbours(cell)) {
                    if (!neighbour.isMarked()) {
                        if (search(neighbour, restOfWord)) {
                            return true;
                        }
                    }
                }
            }
            cell.setMarked(false);
        }
        return false;
    }

    private static final Cell[][] GRID = buildSearchGrid(new String[]{"ABCE", "SFCS", "ADEE"});

    private static Cell[][] buildSearchGrid(String[] strings) {
        int nRows = strings.length;
        int nCols = strings[0].length();
        Cell[][] grid = new Cell[nRows][nCols];
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                grid[r][c] = new Cell(r, c, strings[r].charAt(c));
            }
        }
        return grid;
    }

    private static final int[] OFFSETS = {-1, 0, 1};

    private static Cell[] neighbours(Cell cell) {
        int nRows = GRID.length;
        int nCols = GRID[0].length;
        List<Cell> neighbours = new ArrayList<>();
        for (int dr : OFFSETS) {
            for (int dc : OFFSETS) {
                if (dr == 0 && dc == 0) {
                    continue;
                }
                if (dr == 0 || dc == 0) {
                    int r = cell.getRow() + dr;
                    int c = cell.getCol() + dc;
                    if (r >= 0 && r < nRows && c >= 0 && c < nCols) {
                        neighbours.add(GRID[r][c]);
                    }

                }
            }
        }
        return neighbours.toArray(new Cell[neighbours.size()]);
    }

    private static void unmarkAll() {
        int nRows = GRID.length;
        int nCols = GRID[0].length;
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < nCols; c++) {
                GRID[r][c].setMarked(false);
            }
        }
    }

    private static class Cell {
        private int row;
        private int col;
        private char ch;
        private boolean marked;

        Cell(int row, int col, char ch) {
            this.row = row;
            this.col = col;
            this.ch = ch;
            this.marked = false;
        }

        private int getRow() {
            return row;
        }

        private int getCol() {
            return col;
        }

        private char getCh() {
            return ch;
        }

        private boolean isMarked() {
            return marked;
        }

        private void setMarked(boolean marked) {
            this.marked = marked;
        }
    }
}
