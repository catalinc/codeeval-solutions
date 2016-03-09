import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.BitSet;

public class array_absurdity {

    public static int findDuplicate(final int[] a) {
        final BitSet numbers = new BitSet(a.length);
        for (final int n : a) {
            if (numbers.get(n)) {
                return n;
            }
            numbers.set(n);
        }
        throw new RuntimeException("no duplicate in " + Arrays.toString(a));
    }

    private static int[] parseIntArray(final String s) {
        final String[] tokens = s.split(";");
        final int size = Integer.valueOf(tokens[0]);
        final String[] elements = tokens[1].split(",");
        final int[] a = new int[size];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.valueOf(elements[i]);
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + array_absurdity.class.getName() + " filename");
            System.exit(1);
        }
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    final int[] a = parseIntArray(line);
                    out.println(findDuplicate(a));
                }
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
