import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class armstrong {

    public static boolean isArmstrongNumber(int n) {
        ArrayList<Integer> digits = digits(n);
        return sumOfPowers(digits, digits.size()) == n;
    }

    private static int sumOfPowers(ArrayList<Integer> digits, int power) {
        int result = 0;
        for (int n : digits) {
            result += Math.pow(n, power);
        }
        return result;
    }

    private static ArrayList<Integer> digits(int n) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
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
                if (isArmstrongNumber(n)) {
                    out.println("True");
                } else {
                    out.println("False");
                }
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
