import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Solution for http://codeeval.com/open_challenges/57/
 */
public class spiral_printing {

    public static void printSpiral(String[][] matrix, final PrintWriter out) {
        while (!isEmpty(matrix)) {
            printFirstRow(matrix, out);
            matrix = removeFirstRow(matrix);
            if (!isEmpty(matrix)) {
                out.print(' ');
                matrix = rotateCounterClockWise(matrix);
            }
        }
    }

    private static boolean isEmpty(final String[][] matrix) {
        return matrix.length == 0;
    }

    private static void printFirstRow(final String[][] matrix, final PrintWriter out) {
        final String[] firstRow = matrix[0];
        for (int i = 0; i < firstRow.length; i++) {
            out.print(firstRow[i]);
            if (i != firstRow.length - 1) {
                out.print(' ');
            }
        }
    }

    private static String[][] removeFirstRow(final String[][] matrix) {
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final String[][] newMatrix = new String[rows - 1][cols];
        System.arraycopy(matrix, 1, newMatrix, 0, rows - 1);
        return newMatrix;
    }

    private static String[][] rotateCounterClockWise(final String[][] matrix) {
        final int rows = matrix.length;
        final int cols = matrix[0].length;
        final String[][] rotated = new String[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = cols - 1; j >= 0; j--) {
                rotated[cols - 1 - j][i] = matrix[i][j];
            }
        }
        return rotated;
    }

    private static String[][] parseMatrix(final String s) {
        final String[] tokens = s.split(";");
        final int rows = Integer.valueOf(tokens[0]);
        final int cols = Integer.valueOf(tokens[1]);
        final String[][] matrix = new String[rows][cols];
        final Scanner scanner = new Scanner(tokens[2]);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.next();
            }
        }
        return matrix;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + spiral_printing.class.getName() + " filename");
            System.exit(1);
        }
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            String line;
            while ((line = in.readLine()) != null) {
                printSpiral(parseMatrix(line), out);
                out.println();
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
