import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class next_number {

    private static final int MAX = 1000000;

    private static final int[] DIGIT_COUNT = new int[10];
    private static final int[] DIGIT_COUNT_NEXT = new int[10];

    public static int nextNumber(int n) {
        countDigitOccurrences(n, DIGIT_COUNT);
        for (int next = n + 1; next < MAX; next++) {
            countDigitOccurrences(next, DIGIT_COUNT_NEXT);
            if (equalDigitCount(DIGIT_COUNT, DIGIT_COUNT_NEXT)) {
                return next;
            }
        }
        throw new RuntimeException("no next number found for: " + n);
    }

    private static void countDigitOccurrences(int n, int[] digitCount) {
        Arrays.fill(digitCount, 0);
        while (n > 0) {
            digitCount[n % 10]++;
            n /= 10;
        }
    }

    private static boolean equalDigitCount(int[] digitCount1, int[] digitCount2) {
        for (int i = 1; i < 10; i++) {
            if (digitCount1[i] != digitCount2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                out.println(nextNumber(n));
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