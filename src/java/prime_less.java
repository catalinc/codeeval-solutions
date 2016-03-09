import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Scanner;

public class prime_less {

    public static void solve(final LinkedList<Integer> numbers, final PrintWriter out) {
        final int max = max(numbers);
        final BitSet nonPrimes = new BitSet(max + 1);
        for (int p = 2; p * p <= max; p = nonPrimes.nextClearBit(p + 1)) {
            for (int i = p * p; i <= max; i += p) {
                nonPrimes.set(i);
            }
        }
        for (int i : numbers) {
            final LinkedList<Integer> primesLessThan = new LinkedList<Integer>();
            for (int p = 2; p <= i; p = nonPrimes.nextClearBit(p + 1)) {
                primesLessThan.add(p);
            }
            out.println(join(primesLessThan, ','));
        }
    }

    private static int max(Iterable<Integer> numbers) {
        int max = 0;
        for (int i : numbers) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private static String join(final LinkedList<Integer> strings, final char sep) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(strings.get(i));
            if (i != strings.size() - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            final LinkedList<Integer> numbers = new LinkedList<Integer>();
            while (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            }
            solve(numbers, out);
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
