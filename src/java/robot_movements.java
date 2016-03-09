public class robot_movements {

    private static boolean[][] copy(final boolean[][] a) {
        final int rows = a.length;
        final int cols = a[0].length;
        final boolean[][] copyOfA = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(a[i], 0, copyOfA[i], 0, cols);
        }
        return copyOfA;
    }

    public static int countRecursive(final int r, final int c, final boolean[][] a) {
        final int rows = a.length;
        final int cols = a[0].length;

        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return 0;
        }

        if (r == rows - 1 && c == cols - 1) {
            return 1;
        }

        if (a[r][c]) {
            return 0;
        }

        final boolean[][] copyOfA = copy(a);
        copyOfA[r][c] = true;
        return countRecursive(r + 1, c, copyOfA) +
                countRecursive(r - 1, c, copyOfA) +
                countRecursive(r, c - 1, copyOfA) +
                countRecursive(r, c + 1, copyOfA);
    }

    public static void main(String[] args) {
        final boolean [][] grid = new boolean[4][4];
        System.out.println(countRecursive(0, 0, grid));
    }

}
