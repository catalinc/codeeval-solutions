import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class pass_triangle {

    public static void main(String[] args) throws FileNotFoundException {
        int[][] triangle = readTriangle(args[0]);
        System.out.println(maxSum(triangle));
    }

    private static int maxSum(int[][] triangle) {
        int numRows = triangle.length;
        int[][] sums = new int[numRows][];
        sums[0] = new int[1];
        sums[0][0] = triangle[0][0];
        for (int row = 1; row < numRows; row++) {
            int numCols = triangle[row].length;
            sums[row] = new int[numCols];
            for (int col = 0; col < numCols; col++) {
                if (col == 0) {
                    sums[row][col] = triangle[row][col] + sums[row - 1][col];
                } else if (col == numCols - 1) {
                    sums[row][col] = triangle[row][col] + sums[row - 1][col - 1];
                } else {
                    sums[row][col] = triangle[row][col] + Math.max(sums[row - 1][col - 1], sums[row - 1][col]);
                }
            }
        }
        return max(sums[numRows - 1]);
    }

    private static int max(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        return max;
    }

    private static int[][] readTriangle(String fileName) throws FileNotFoundException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            List<int[]> triangle = new ArrayList<int[]>();
            int size = 0;
            while (scanner.hasNextInt()) {
                size++;
                int[] row = new int[size];
                for (int i = 0; i < size; i++) {
                    row[i] = scanner.nextInt();
                }
                triangle.add(row);
            }
            return triangle.toArray(new int[triangle.size()][]);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

}
