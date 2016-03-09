import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameOfLife {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line = reader.readLine();
            int n = line.length();
            byte[][] grid = new byte[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = (byte) (line.charAt(j) == '*' ? 1 : 0);
                }
                line = reader.readLine();
            }

            byte[][] temp = new byte[n][n];
            for (int i = 0; i < 10; i++) {
                gameOfLife(grid, temp);
            }

            StringBuilder sb = new StringBuilder((n + 1) * n);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(grid[i][j] == 1 ? '*' : '.');
                }
                sb.append('\n');
            }
            System.out.print(sb);
        }
    }

    private static void gameOfLife(byte[][] grid, byte[][] temp) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbours = liveNeighbours(grid, i, j);
                if (liveNeighbours < 2 || liveNeighbours > 3) {
                    temp[i][j] = 0;
                } else if (liveNeighbours == 3 && grid[i][j] == 0) {
                    temp[i][j] = 1;
                } else if ((liveNeighbours == 2 || liveNeighbours == 3) && grid[i][j] == 1) {
                    temp[i][j] = 1;
                } else {
                    temp[i][j] = grid[i][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(temp[i], 0, grid[i], 0, n);
        }
    }

    private static int liveNeighbours(byte[][] grid, int i, int j) {
        int alive = 0;
        for (int k = -1; k <= 1; k++) {
            for (int t = -1; t <= 1; t++) {
                if (k == 0 && t == 0) continue;
                int ik = i + k;
                int jt = j + t;
                if (ik < 0 || ik >= grid.length) continue;
                if (jt < 0 || jt >= grid.length) continue;
                if (grid[ik][jt] == 1) alive++;
            }
        }
        return alive;
    }
}
