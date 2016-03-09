import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution to https://www.codeeval.com/open_challenges/105/
 */
public final class LargestSubMatrix {
    public static void main(String[] args) throws IOException {
        int[][] matrix = parseMatrix(args[0]);
        System.out.println(computeMaxSum(matrix));
    }

    private static int[][] parseMatrix(String filename) throws IOException {
        int[][] m = null;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                int[] row = parseIntArray(line);
                if (i == 0) {
                    m = new int[row.length][];
                }
                m[i] = row;
                i++;
            }
        }
        return m;
    }

    private static int[] parseIntArray(String str) {
        String[] tokens = str.split(" ");
        int[] a = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            a[i] = Integer.parseInt(tokens[i]);
        }
        return a;
    }

    private static int computeMaxSum(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                for (int i = r + 1; i < matrix.length; i++) {
                    for (int j = c + 1; j < matrix.length; j++) {
                        int sum = computeMaxSum(matrix, r, c, i, j);
                        if (sum > max) {
                            max = sum;
                        }
                    }
                }
            }
        }
        return max;
    }

    private static int computeMaxSum(int[][] matrix, int rowStart, int colStart, int rowEnd, int colEnd) {
        int sum = 0;
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
}
