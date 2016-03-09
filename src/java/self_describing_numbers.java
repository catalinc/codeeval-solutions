import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class self_describing_numbers {

    public static boolean isSelfDescribing(int n) {
        List<Integer> digitsOfN = digits(n);
        for (int i = 0; i < digitsOfN.size(); i++) {
            int value = digitsOfN.get(i);
            if (value != countOccurrences(digitsOfN, i)) {
                return false;
            }
        }
        return true;
    }

    private static int countOccurrences(List<Integer> numbers, int n) {
        int count = 0;
        for (int i : numbers) {
            if (i == n) {
                count++;
            }
        }
        return count;
    }

    private static List<Integer> digits(int n) {
        List<Integer> digits = new LinkedList<Integer>();
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
                int selfDescribing = 0;
                if (isSelfDescribing(n)) {
                    selfDescribing = 1;
                }
                out.println(selfDescribing);
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
