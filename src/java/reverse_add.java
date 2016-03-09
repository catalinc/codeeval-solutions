import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class reverse_add {

    public static int[] reverseAndAddUntilIsPalindrome(int n) {
        n = n + reverse(n);
        int iterations = 1;
        while (!isPalindrome(n)) {
            n = n + reverse(n);
            iterations++;
        }
        return new int[]{iterations, n};
    }

    private static boolean isPalindrome(final int n) {
        final Integer[] digitsOfN = digits(n);
        for (int i = 0, j = digitsOfN.length - 1; i < j; i++, j--) {
            if (!digitsOfN[i].equals(digitsOfN[j])) {
                return false;
            }
        }
        return true;
    }

    private static Integer[] digits(int n) {
        final List<Integer> digits = new LinkedList<Integer>();
        while (n > 0) {
            digits.add(0, n % 10);
            n = n / 10;
        }
        return digits.toArray(new Integer[digits.size()]);
    }

    private static int reverse(final int n) {
        final Integer[] digitsOfN = digits(n);
        int result = digitsOfN[digitsOfN.length - 1];
        for (int i = digitsOfN.length - 2; i >= 0; i--) {
            result = result * 10 + digitsOfN[i];
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                final int n = scanner.nextInt();
                final int[] result = reverseAndAddUntilIsPalindrome(n);
                out.printf("%d %d\n", result[0], result[1]);
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
