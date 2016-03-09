import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class min_coins {

    private static final int[] COIN_VALUES = new int[]{0, 1, 3, 5};

    private static final int INFINITY = 1000000;

    public static int minCoins(int total) {
        int[] minCoins = new int[total + 1];
        Arrays.fill(minCoins, INFINITY);
        minCoins[0] = 0;
        for (int sum = 1; sum <= total; sum++) {
            for (int value : COIN_VALUES) {
                if (value <= sum && minCoins[sum - value] + 1 < minCoins[sum]) {
                    minCoins[sum] = minCoins[sum - value] + 1;
                }
            }
        }
        return minCoins[total];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                int total = scanner.nextInt();
                out.println(minCoins(total));
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

}
