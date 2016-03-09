import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class multiples {

    public static int smallestMultiple(int x, int n) {
        while (n < x) {
            n *= 2;
        }
        return n;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                int x = Integer.valueOf(tokens[0]);
                int n = Integer.valueOf(tokens[1]);
                out.println(smallestMultiple(x, n));
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
