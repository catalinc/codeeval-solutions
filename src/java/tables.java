import java.io.PrintWriter;

public class tables {

    private static int[][] multiplicationTable(int n) {
        int[][] m = new int[n][n];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                m[i - 1][j - 1] = i * j;
            }
        }
        return m;
    }

    private static String prettyPrint(int[][] m) {
        StringBuilder result = new StringBuilder();
        int size = m.length;
        for (int i = 0; i < size; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < size; j++) {
                line.append(String.format("%4d", m[i][j]));
            }
            result.append(line.toString().trim());
            if (i != size - 1) {
                result.append('\n');
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(System.out);
            int[][] table = multiplicationTable(12);
            out.print(prettyPrint(table));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
