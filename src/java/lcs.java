import java.io.*;

/**
 * Solution for http://www.codeeval.com/open_challenges/6/
 */
public class lcs {

    public static String LCS(final String s1, final String s2) {
        final int[][] m = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            final int r = i + 1;
            for (int j = 0; j < s2.length(); j++) {
                final int c = j + 1;
                if (s1.charAt(i) == s2.charAt(j)) {
                    m[r][c] = m[r - 1][c - 1] + 1;
                } else {
                    m[r][c] = Math.max(m[r][c - 1], m[r - 1][c]);
                }
            }
        }

        final StringBuilder ret = new StringBuilder();
        for (int r = s1.length(), c = s2.length(); r > 0 && c > 0; ) {
            if (s1.charAt(r - 1) == s2.charAt(c - 1)) {
                ret.append(s1.charAt(r - 1));
                r--;
                c--;
            } else {
                if (m[r][c - 1] > m[r - 1][c]) {
                    c--;
                } else {
                    r--;
                }
            }
        }

        return ret.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + lcs.class.getName() + " filename");
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
                    final String[] tokens = line.split(";");
                    out.println(LCS(tokens[0], tokens[1]));
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
