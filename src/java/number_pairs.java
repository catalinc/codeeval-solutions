import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class number_pairs {

    public static List<Pair> numberPairs(final int[] a, final int sum) {
        final List<Pair> pairs = new LinkedList<Pair>();
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            final int n = a[lo] + a[hi];
            if (n == sum) {
                pairs.add(new Pair(a[lo], a[hi]));
                lo++;
                hi--;
            } else if (n < sum) {
                lo++;
            } else {
                hi--;
            }
        }
        return pairs;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine().trim();
                if (line.length() == 0) {
                    continue;
                }
                final int semicolonPos = line.indexOf(';');
                final int[] a = parseArray(line, semicolonPos);
                final int sum = parseSum(line, semicolonPos);
                final List<Pair> pairs = numberPairs(a, sum);
                if (pairs.isEmpty()) {
                    out.println("NULL");
                } else {
                    final StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < pairs.size(); i++) {
                        sb.append(pairs.get(i));
                        if (i != pairs.size() - 1) {
                            sb.append(';');
                        }
                    }
                    out.println(sb.toString());
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

    private static int[] parseArray(final String line, int semicolonPos) {
        final String s = line.substring(0, semicolonPos);
        final String[] tokens = s.split(",");
        final int[] a = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            a[i] = Integer.valueOf(tokens[i]);
        }
        return a;
    }

    private static int parseSum(final String line, int semicolonPos) {
        final String s = line.substring(semicolonPos + 1);
        return Integer.valueOf(s);
    }

}

class Pair {
    int first;
    int second;

    public Pair(final int first, final int second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return first + "," + second;
    }
}