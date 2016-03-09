import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Solution for http://codeeval.com/open_challenges/17/
 */
public class largest_sum {

    public static int maxSumSubArray(final int[] a) {
        int max = a[0];
        int maxEndingHere = a[0];
        for (int i = 1; i < a.length; i++) {
            maxEndingHere = Math.max(a[i], a[i] + maxEndingHere);
            max = Math.max(max, maxEndingHere);
        }
        return max;
    }

    private static int[] parseIntArray(final String s) {
        final String[] tokens = s.split(",");
        final int[] a = new int[tokens.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.valueOf(tokens[i].trim());
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + largest_sum.class.getName() + " filename");
            System.exit(1);
        }
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            String line;
            while ((line = in.readLine()) != null) {
                final int[] a = parseIntArray(line);
                out.println(maxSumSubArray(a));
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
