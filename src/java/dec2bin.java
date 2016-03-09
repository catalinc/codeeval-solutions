import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class dec2bin {

    public static String toBin(int n) {
        final StringBuilder sb = new StringBuilder();
        while (n > 0) {
            final int b = n & 1;
            sb.insert(0, b);
            n = n >> 1;
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                final int n = scanner.nextInt();
                out.println(toBin(n));
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
