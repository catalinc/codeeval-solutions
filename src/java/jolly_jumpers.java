import java.io.*;
import java.util.*;

public class jolly_jumpers {

    public static boolean isJollyJumper(final int[] a) {
        if (a.length == 1) {
            return true;
        }
        final BitSet diffs = new BitSet(a.length);
        for (int i = 1; i < a.length; i++) {
            final int diff = Math.abs(a[i] - a[i - 1]);
            if (diff <= 0 || diff >= a.length) {
                return false;
            }
            if (diffs.get(diff)) {
                return false;
            }
            diffs.set(diff);
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + jolly_jumpers.class.getName() + " filename");
            System.exit(1);
        }
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while(scanner.hasNextInt()) {
                final int size = scanner.nextInt();
                final int[] a = new int[size];
                for(int i = 0; i < size; i++) {
                    a[i] = scanner.nextInt();
                }
                if(isJollyJumper(a)) {
                    out.println("Jolly");
                } else {
                    out.println("Not jolly");
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

}
