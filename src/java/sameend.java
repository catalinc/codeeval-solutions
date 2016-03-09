import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class sameend {

    public static boolean endsWith(final String s1, final String s2) {
        if (s2.length() > s1.length()) {
            return false;
        }
        for (int i = s1.length() - 1, j = s2.length() - 1; i >= 0 && j >= 0; i--, j--) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                if (line.length() == 0) {
                    continue;
                }
                final String[] strings = line.split(",");
                if (endsWith(strings[0], strings[1])) {
                    out.println(1);
                } else {
                    out.println(0);
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
