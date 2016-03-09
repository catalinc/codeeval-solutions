import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MinSumPath {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            scanner.useDelimiter("[,\\s]+");
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                int[][] mat = new int[n][n];
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        mat[r][c] = scanner.nextInt();
                    }
                }
                System.out.println(minPathSum(mat, n));
            }
        }
    }

    private static int minPathSum(int[][] mat, int n) {
        return minPathSum(0, 0, mat, n);
    }

    private static int minPathSum(int r, int c, int[][] mat, int n) {
        if (r >= n || c >= n) {
            return Integer.MAX_VALUE;
        }
        if (r == n - 1 && c == n - 1) {
            return mat[r][c];
        }
        return mat[r][c] + Math.min(minPathSum(r + 1, c, mat, n), minPathSum(r, c + 1, mat, n));
    }
}
