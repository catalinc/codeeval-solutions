import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class position {

    public static boolean testBits(int n, int p1, int p2) {
        return bitAt(n, p1) == bitAt(n, p2);
    }

    private static boolean bitAt(int n, int p) {
        return (n & (1 << (p - 1))) != 0; // p is 1 based
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
                int n = Integer.valueOf(tokens[0]);
                int p1 = Integer.valueOf(tokens[1]);
                int p2 = Integer.valueOf(tokens[2]);
                out.println(testBits(n, p1, p2));
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
