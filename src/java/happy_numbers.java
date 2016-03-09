import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class happy_numbers {

    public static boolean isHappy(int n) {
        Set<Integer> numbersSoFar = new HashSet<>();
        while (true) {
            if (n == 1) {
                return true;
            }
            if (numbersSoFar.contains(n)) {
                return false;
            } else {
                numbersSoFar.add(n);
            }
            n = sumOfSquares(digits(n));
        }
    }

    private static int sumOfSquares(Iterable<Integer> values) {
        int sum = 0;
        for (Integer i : values) {
            sum += i * i;
        }
        return sum;
    }

    private static LinkedList<Integer> digits(int n) {
        LinkedList<Integer> digits = new LinkedList<>();
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
                int happy = 0;
                if (isHappy(n)) {
                    happy = 1;
                }
                out.println(happy);
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
