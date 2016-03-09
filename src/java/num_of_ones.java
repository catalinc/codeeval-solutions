import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class num_of_ones {

    public static int countOnes(int i) {
        i = i - ((i >> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
        return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + num_of_ones.class.getName() + " filename");
            System.exit(1);
        }
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                final int n = scanner.nextInt();
                out.println(countOnes(n));
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
