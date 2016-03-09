import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class substring {

    public static boolean match(final String regex, final String s) {
        for (int i = 0; i < s.length(); i++) {
            if (matchHere(regex, 0, s, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean matchHere(final String regex,
                                     final int i,
                                     final String s,
                                     final int j) {
        if (i == regex.length()) {
            return true;
        }
        if (j == s.length()) {
            return false;
        }
        if (regex.charAt(i) == '\\' && i < regex.length() - 1 && regex.charAt(i + 1) == '*') {
            return matchHere(regex, i + 1, s, j);
        }
        if (regex.charAt(i) == '*') {
            if (i > 0 && regex.charAt(i - 1) == '\\') {
                if (regex.charAt(i) == s.charAt(j)) {
                    return matchHere(regex, i + 1, s, j + 1);
                }
                return false;
            }
            return matchStar(regex, i + 1, s, j);
        }
        if (regex.charAt(i) == s.charAt(j)) {
            return matchHere(regex, i + 1, s, j + 1);
        }
        return false;
    }

    private static boolean matchStar(final String regex,
                                     final int i,
                                     final String s,
                                     final int j) {
        if (i == regex.length()) {
            return true;
        }
        if (j == s.length()) {
            return false;
        }
        if (regex.charAt(i) == s.charAt(j)) {
            return matchHere(regex, i + 1, s, j + 1);
        }
        return matchStar(regex, i, s, j + 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final String[] strings = line.split(",");
                out.println(match(strings[1], strings[0]));
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
