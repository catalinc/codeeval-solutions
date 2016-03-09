import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class cycle_detection {

    public static Integer[] findCycle(final int[] a) {
        final List<Integer> cycle = new LinkedList<Integer>();
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] == a[j]) {
                    int k = 0;
                    while (i + k < j) {
                        if (j + k >= a.length) {
                            cycle.clear();
                            break;
                        } else if (a[i + k] == a[j + k]) {
                            cycle.add(a[i + k]);
                            k++;
                        } else {
                            cycle.clear();
                            break;
                        }
                    }
                    return cycle.toArray(new Integer[cycle.size()]);
                }
            }
        }
        return cycle.toArray(new Integer[cycle.size()]);
    }

    private static int[] parseIntArray(final String s) {
        final String[] tokens = s.split(" ");
        final int[] a = new int[tokens.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.valueOf(tokens[i].trim());
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + cycle_detection.class.getName() + " filename");
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
                final Integer[] cycle = findCycle(a);
                if (cycle.length > 0) {
                    out.println(join(cycle, ' '));
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

    private static String join(final Integer[] a, final char sep) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i != a.length - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

}
