import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FindMin {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            scanner.useDelimiter(Pattern.compile("[\\r\\n,]+"));
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                int k = scanner.nextInt();
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int c = scanner.nextInt();
                int r = scanner.nextInt();

                int[] m = computeArray(n, k, a, b, c, r);
                System.out.println(m[n - 1]);
            }
        }
    }

    private static int[] computeArray(int n, int k, int a, int b, int c, int r) {
        int[] m = new int[n];
        m[0] = a;
        for (int i = 1; i < k; i++) {
            m[i] = (b * m[i - 1] + c) % r;
        }
        for (int i = k; i < n; i++) {
            m[i] = findMin(m, i - k, i);
        }
        return m;
    }

    private static int findMin(int[] m, int lo, int hi) {
        int min = -1;
        boolean found = false;
        while (!found) {
            min++;
            found = true;
            for (int i = lo; i < hi; i++) {
                if (m[i] == min) {
                    found = false;
                    break;
                }
            }
        }
        return min;
    }

}
