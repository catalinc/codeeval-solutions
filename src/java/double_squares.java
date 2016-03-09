import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class double_squares {

    /**
     * Implementation of Gauss formula to count number of ways a
     * natural number can be expressed as a sum of natural squares.
     * <p/>
     * See http://mathforum.org/library/drmath/view/60738.html
     */
    public static int sumOfSquaresRepresentations(final int n) {
        final HashMap<Integer, MutableInt> factors = primeFactors(n);

        final LinkedList<Integer> exponents = new LinkedList<Integer>();
        for (Map.Entry<Integer, MutableInt> factor : factors.entrySet()) {
            final int prime = factor.getKey();
            final int exponent = factor.getValue().value;
            if (prime % 4 == 3 && exponent % 2 != 0) {
                return 0;
            }
            if (prime % 4 == 1) {
                exponents.add(exponent);
            }
        }

        int productOfExponents = 1;
        for (int e : exponents) {
            productOfExponents *= e + 1;
        }

        boolean allEven = true;
        for (int e : exponents) {
            if (e % 2 != 0) {
                allEven = false;
                break;
            }
        }

        if (allEven) {
            productOfExponents += 1;
        }

        return productOfExponents / 2;
    }

    public static HashMap<Integer, MutableInt> primeFactors(int n) {
        final HashMap<Integer, MutableInt> factors = new HashMap<Integer, MutableInt>();
        for (int d = 2; d <= n / d; d++) {
            while (n % d == 0) {
                MutableInt exponent = factors.get(d);
                if (exponent == null) {
                    exponent = new MutableInt(0);
                    factors.put(d, exponent);
                }

                exponent.value++;
                n /= d;
            }
        }
        if (n > 1) {
            factors.put(n, new MutableInt(1));
        }
        return factors;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            final int count = scanner.nextInt();
            for (int i = 0; i < count; i++) {
                final int n = scanner.nextInt();
                out.println(sumOfSquaresRepresentations(n));
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

class MutableInt {
    int value;

    MutableInt(int value) {
        this.value = value;
    }
}