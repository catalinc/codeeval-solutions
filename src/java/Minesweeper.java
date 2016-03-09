import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Minesweeper {

    private static char[][] parseMineField(String input) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter(",|;");
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            String matrixInRowMajorForm = scanner.next();
            char[][] mineField = new char[rows][cols];
            int i = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    mineField[r][c] = matrixInRowMajorForm.charAt(i);
                    i++;
                }
            }
            return mineField;
        }
    }

    private static char[][] countMines(char[][] mineField) {
        int rows = mineField.length;
        int cols = mineField[0].length;
        char[][] result = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mineField[r][c] == '*') {
                    result[r][c] = '*';
                } else {
                    int count = countAdjacentMines(r, c, mineField);
                    result[r][c] = Character.forDigit(count, 10);
                }
            }
        }
        return result;
    }

    private static int countAdjacentMines(int row, int col, char[][] mineField) {
        int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) continue;
                int newRow = row + rowOffset;
                int newCol = col + colOffset;
                if (newRow < 0 || newRow >= mineField.length) continue;
                if (newCol < 0 || newCol >= mineField[0].length) continue;
                if (mineField[newRow][newCol] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    private static void printMatrixInRowMajorForm(char[][] matrix) {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                result.append(matrix[row][col]);
            }
        }
        System.out.println(result.toString());
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                char[][] mineField = parseMineField(scanner.nextLine());
                char[][] mineCount = countMines(mineField);
                printMatrixInRowMajorForm(mineCount);
            }
        }
    }
}
