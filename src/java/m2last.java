import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Solution for http://codeeval.com/open_challenges/10/
 */
public class m2last {

    public static void mth2last(final String s, final PrintWriter out) {
        final String[] tokens = s.split(" ");
        final String last = tokens[tokens.length - 1];
        final int index = tokens.length - 1 - Integer.valueOf(last);
        if (index >= 0 && index < tokens.length - 1) {
            out.println(tokens[index]);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + m2last.class.getName() + " filename");
            System.exit(1);
        }
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            String line;
            while ((line = in.readLine()) != null) {
                mth2last(line, out);
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
