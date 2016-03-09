import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/open_challenges/151/
 */
public final class CrackingEggs {

    private static int eggsDrop(int n, int k) {
        int[][] m = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            m[i][1] = 1;
            m[i][0] = 0;
        }

        for (int j = 1; j <= k; j++) {
            m[1][j] = j;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                m[i][j] = Integer.MAX_VALUE;
                for (int x = 1; x <= j; x++) {
                    int result = 1 + Math.max(m[i - 1][x - 1], m[i][j - x]);
                    if (result < m[i][j]) {
                        m[i][j] = result;
                    }
                }
            }
        }

        return m[n][k];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int n = Integer.parseInt(tokens[0]);
                int k = Integer.parseInt(tokens[1]);
                System.out.println(eggsDrop(n, k));
            }
        }
    }
}
