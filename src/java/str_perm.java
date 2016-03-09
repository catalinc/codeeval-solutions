import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Solution for http://www.codeeval.com/public_sc/14/
 */
public class str_perm {

    /**
     * Algorithm taken from <a href="http://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order">Wikipedia</a>
     */
    public static void permutations(final String str, final PrintWriter out) {
        final char[] chars = str.toCharArray();
        Arrays.sort(chars);
        out.print(new String(chars));

        while (true) {
            int k = -1;
            int l = -1;
            for (int i = chars.length - 2; i >= 0; i--) {
                if (chars[i] < chars[i + 1]) {
                    k = i;
                    break;
                }
            }
            if (k < 0) {
                break;
            }
            for (int i = chars.length - 1; i > k; i--) {
                if (chars[i] > chars[k]) {
                    l = i;
                    break;
                }
            }
            swap(chars, k, l);
            reverse(chars, k + 1, chars.length - 1);
            out.print("," + new String(chars));
        }
        out.println();
        out.flush();
    }

    private static void swap(final char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void reverse(final char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + str_perm.class.getName() + " filename");
            System.exit(1);
        }
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            String line;
            while ((line = in.readLine()) != null) {
                permutations(line, out);
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
