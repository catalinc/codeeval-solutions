import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Solution for http://codeeval.com/open_challenges/53/
 */
public class repeated_substr {

    public static String longestRepeated(final String s) {
        final List<Suffix> suffixes = suffixes(s);
        Collections.sort(suffixes, new Comparator<Suffix>() {
            public int compare(Suffix o1, Suffix o2) {
                return o1.string.compareTo(o2.string);
            }
        });

        Suffix best = new Suffix(0, "");
        for (int i = 0; i <= suffixes.size() - 2; i++) {
            final Suffix s1 = suffixes.get(i);
            final Suffix s2 = suffixes.get(i + 1);
            final Suffix commonFromStart = commonNonOverlappingFromStart(s1, s2);
            if (commonFromStart.length() > best.length()) {
                best = commonFromStart;
            } else if (commonFromStart.length() == best.length() && commonFromStart.before(best)) {
                best = commonFromStart;
            }
        }

        return best.string;
    }

    private static List<Suffix> suffixes(final String s) {
        final List<Suffix> suffixes = new ArrayList<Suffix>(s.length());
        for (int i = s.length() - 1; i >= 0; i--) {
            suffixes.add(new Suffix(i, s.substring(i)));
        }
        return suffixes;
    }

    private static Suffix commonNonOverlappingFromStart(final Suffix suffix1,
                                                        final Suffix suffix2) {
        Suffix first;
        Suffix second;
        if (suffix1.before(suffix2)) {
            first = suffix1;
            second = suffix2;
        } else {
            first = suffix2;
            second = suffix1;
        }

        final int start = first.start;
        final int max = Math.min(first.length(), second.length());
        final StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < max && start + i < second.start; i++) {
            final char ch1 = first.string.charAt(i);
            final char ch2 = second.string.charAt(i);
            if (ch1 != ch2) {
                break;
            }
            prefix.append(ch1);
        }
        return new Suffix(start, prefix.toString());
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + repeated_substr.class.getName() + " filename");
            System.exit(1);
        }
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            String line;
            while ((line = in.readLine()) != null) {
                final String longestRepeated = longestRepeated(line);
                if (longestRepeated.trim().length() > 0) {
                    out.println(longestRepeated);
                } else {
                    out.println("NONE");
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

class Suffix {
    final int start;
    final String string;

    Suffix(final int start, final String string) {
        this.start = start;
        this.string = string;
    }

    boolean before(final Suffix other) {
        return this.start <= other.start;
    }

    int length() {
        return string.length();
    }
}