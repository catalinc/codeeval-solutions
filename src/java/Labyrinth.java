import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Solution to https://www.codeeval.com/open_challenges/157/
 */
public class Labyrinth {

    private static int[][] readLabyrinth(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        int rows = lines.size();
        int[][] labyrinth = new int[rows][];
        for (int r = 0; r < rows; r++) {
            String line = lines.get(r);
            int cols = line.length();
            labyrinth[r] = new int[cols];
            for (int c = 0; c < cols; c++) {
                labyrinth[r][c] = line.charAt(c) == '*' ? 1 : 0;
            }
        }
        return labyrinth;
    }

    private static String toString(int[][] labyrinth) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < labyrinth.length; r++) {
            for (int c = 0; c < labyrinth[r].length; c++) {
                if (labyrinth[r][c] == 0) {
                    sb.append(' ');
                } else if (labyrinth[r][c] == 1) {
                    sb.append('*');
                } else if (labyrinth[r][c] == 2) {
                    sb.append('+');
                }
            }
            if (r != labyrinth.length - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    private static void findShortestPath(int[][] labyrinth) {
        int[][] level = new int[labyrinth.length][labyrinth[0].length];
        for (int r = 0; r < labyrinth.length; r++) {
            for (int c = 0; c < labyrinth[r].length; c++) {
                level[r][c] = labyrinth[r][c] == 1 ? -1 : 0;
            }
        }

        Cell start = findFirstPass(labyrinth, 0);
        Cell end = findFirstPass(labyrinth, labyrinth.length - 1);

        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);
        level[start.row][start.col] = 1;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            if (cell.equals(end)) break;

            int cellLevel = level[cell.row][cell.col];

            Cell[] nextCells = new Cell[4];
            nextCells[3] = new Cell(cell.row, cell.col - 1);
            nextCells[2] = new Cell(cell.row - 1, cell.col);
            nextCells[1] = new Cell(cell.row, cell.col + 1);
            nextCells[0] = new Cell(cell.row + 1, cell.col);

            for (Cell nextCell : nextCells) {
                if (nextCell.row < 0 || nextCell.col < 0) continue;
                if (nextCell.row == labyrinth.length || nextCell.col == labyrinth[0].length) continue;
                if (level[nextCell.row][nextCell.col] == 0) {
                    queue.add(nextCell);
                    level[nextCell.row][nextCell.col] = cellLevel + 1;
                }
            }
        }

        if (level[end.row][end.col] == 0) return; // no way out

        LinkedList<Cell> path = new LinkedList<>();

        Cell cell = end;
        while (true) {
            path.push(cell);
            if (cell.equals(start)) break;

            int cellLevel = level[cell.row][cell.col];
            Cell[] nextCells = new Cell[4];
            nextCells[0] = new Cell(cell.row + 1, cell.col);
            nextCells[1] = new Cell(cell.row, cell.col + 1);
            nextCells[2] = new Cell(cell.row - 1, cell.col);
            nextCells[3] = new Cell(cell.row, cell.col - 1);
            for (Cell nextCell : nextCells) {
                if (nextCell.row < 0 || nextCell.col < 0) continue;
                if (nextCell.row == labyrinth.length || nextCell.col == labyrinth[0].length)
                    continue;
                if (level[nextCell.row][nextCell.col] == cellLevel - 1) {
                    cell = nextCell;
                    break;
                }
            }
        }

        for (Cell pass : path) {
            labyrinth[pass.row][pass.col] = 2;
        }
    }

    private static Cell findFirstPass(int[][] labyrinth, int row) {
        for (int col = 0; col < labyrinth[row].length; col++) {
            if (labyrinth[row][col] == 0) {
                return new Cell(row, col);
            }
        }
        return null;
    }

    private static class Cell {

        private final int row;
        private final int col;

        private Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (col != cell.col) return false;
            if (row != cell.row) return false;

            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        int[][] labyrinth = readLabyrinth(args[0]);
        findShortestPath(labyrinth);
        System.out.println(toString(labyrinth));
    }

}
