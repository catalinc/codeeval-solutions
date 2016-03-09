import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Scanner;

public class sudoku {

    public static boolean isSudokuSolution(int[][] matrix, int size) {

        // rows
        for (int i = 0; i < size; i++) {
            BitSet bs = new BitSet(size);
            for (int j = 0; j < size; j++) {
                bs.set(matrix[i][j]);
            }
            if (bs.cardinality() != size) return false;
        }

        // columns
        for (int i = 0; i < size; i++) {
            BitSet bs = new BitSet(size);
            for (int j = 0; j < size; j++) {
                bs.set(matrix[j][i]);
            }
            if (bs.cardinality() != size) return false;
        }

        // regions
        int regionSize = (int) Math.sqrt(size);
        for (int i = 0; i < size; i += regionSize) {
            for (int j = 0; j < size; j += regionSize) {
                BitSet bs = new BitSet(size);
                for (int r = i; r < i + regionSize; r++) {
                    for (int c = j; c < j + regionSize; c++) {
                        bs.set(matrix[r][c]);
                    }
                }
                if (bs.cardinality() != size) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                int size = Integer.parseInt(parts[0]);
                int[][] matrix = parseMatrix(parts[1], size);
                if (isSudokuSolution(matrix, size)) {
                    out.println("True");
                } else {
                    out.println("False");
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    private static int[][] parseMatrix(String s, int size) {
        int[][] matrix = new int[size][size];
        String[] elements = s.split(",");
        for (int i = 0; i < elements.length; i += size) {
            int row = i / size;
            for (int col = 0; col < size; col++) {
                matrix[row][col] = Integer.parseInt(elements[i + col]);
            }
        }
        return matrix;
    }

}
