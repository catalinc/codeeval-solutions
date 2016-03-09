import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Solution to https://www.codeeval.com/open_challenges/218/
 */
public class BuildersTeam {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseMap(line);
                System.out.println(countSquares());
            }
        }
    }

    private static final boolean[][] map = new boolean[25][25];
    private static final int rowSize = (int) Math.sqrt(map.length);


    private static void clearMap() {
        for (boolean[] row : map) {
            Arrays.fill(row, false);
        }
    }

    private static void parseMap(String line) {
        clearMap();
        String[] pipes = line.split(" \\| ");
        for (String pipe : pipes) {
            String[] houses = pipe.split(" ");
            int house1 = Integer.parseInt(houses[0]) - 1;
            int house2 = Integer.parseInt(houses[1]) - 1;
            map[house1][house2] = true;
            map[house2][house1] = true;
        }
    }

    private static int countSquares() {
        int count = 0;
        for (int i = 0; i < map.length - rowSize; i++) {
            int row = i / rowSize + 1;
            int rowEnd = row * rowSize - 1;
            for (int j = 1; (j + i) <= rowEnd; j++) {
                int topLeft = i;
                int topRight = i + j;
                int bottomLeft = i + j * rowSize;
                if (bottomLeft >= map.length) break;
                int bottomRight = i + j * rowSize + j;
                if (isConnectedFromLeftToRight(topLeft, topRight) &&
                        isConnectedFromTopToBottom(topLeft, bottomLeft) &&
                        isConnectedFromLeftToRight(bottomLeft, bottomRight) &&
                        isConnectedFromTopToBottom(topRight, bottomRight)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isConnectedFromTopToBottom(int x, int y) {
        for (int i = x + rowSize; i <= y; i += rowSize) {
            if (!map[x][i]) return false;
            x = i;
        }
        return true;
    }

    private static boolean isConnectedFromLeftToRight(int x, int y) {
        for (int i = x + 1; i <= y; i++) {
            if (!map[x][i]) return false;
            x = i;
        }
        return true;
    }
}
