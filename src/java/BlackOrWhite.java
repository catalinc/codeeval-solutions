import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution to https://www.codeeval.com/open_challenges/209/
 */
public final class BlackOrWhite {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Result result = findSmallestAreaWithEqualNumberOfBlackCars(line);
                System.out.println(result);
            }
        }
    }

    private static final boolean[][] CAR_PARK = new boolean[25][25];
    private static int SIZE = CAR_PARK.length;

    private static Result findSmallestAreaWithEqualNumberOfBlackCars(String line) {
        parseCarPark(line);
        int blackCarsCount;
        next_size:
        for (int n = 1; n < SIZE; n++) {
            blackCarsCount = -1;
            int count = SIZE - n + 1;
            for (int r = 0; r < count; r++) {
                for (int c = 0; c < count; c++) {
                    int blackCarsCountSubMatrix = countBlackCars(r, c, n);
                    if (blackCarsCount == -1) {
                        blackCarsCount = blackCarsCountSubMatrix;
                    } else {
                        if (blackCarsCount != blackCarsCountSubMatrix) continue next_size;
                    }
                }
            }
            return new Result(n, blackCarsCount);
        }
        return new Result(SIZE, countBlackCars(0, 0, SIZE));
    }

    private static int countBlackCars(int r, int c, int size) {
        int blackCarsCount = 0;
        for (int i = r; i < r + size; i++ ) {
            for (int j = c; j < c + size; j++) {
                if (CAR_PARK[i][j]) blackCarsCount++;
            }
        }
        return blackCarsCount;
    }

    private static class Result {
        int size;
        int count;

        public Result(int size, int count) {
            this.size = size;
            this.count = count;
        }

        @Override
        public String toString() {
            return String.format("%dx%d, %d", size, size, count);
        }
    }

    private static void parseCarPark(String str) {
        String[] lines = str.split(" \\| ");
        SIZE = lines.length;
        clearCarParkArea(SIZE);
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                CAR_PARK[i][j] = lines[i].charAt(j) == '1';
            }
        }
    }

    private static void clearCarParkArea(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                CAR_PARK[i][j] = false;
            }
        }
    }
}
