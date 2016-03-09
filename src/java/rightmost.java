import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class rightmost {

    public static int rightmostOccurrence(String s, char t) {
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t) {
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    String[] tokens = line.split(",");
                    String s = tokens[0];
                    char t = tokens[1].charAt(0);
                    out.println(rightmostOccurrence(s, t));
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
