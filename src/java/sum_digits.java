import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class sum_digits {

    public static int sumOfDigits(int n) {
        return sum(digits(n));
    }

    private static int sum(Iterable<Integer> values) {
        int sum = 0;
        for (Integer i : values) {
            sum += i;
        }
        return sum;
    }

    private static LinkedList<Integer> digits(int n) {
        LinkedList<Integer> digits = new LinkedList<Integer>();
        while (n > 0) {
            digits.add(0, n % 10);
            n /= 10;
        }
        return digits;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                out.println(sumOfDigits(n));
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
