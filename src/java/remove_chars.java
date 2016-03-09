import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class remove_chars {

    public static String removeChars(final String s, final char[] charsToRemove) {
        final Set<Character> toRemove = new HashSet<Character>();
        for (char aCharsToRemove : charsToRemove) {
            toRemove.add(aCharsToRemove);
        }
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (!toRemove.contains(c)) {
                result.append(c);
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                final String[] tokens = scanner.nextLine().split(",");
                final String s = tokens[0].trim();
                final char[] charsToRemove = tokens[1].trim().toCharArray();
                out.println(removeChars(s, charsToRemove));
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
