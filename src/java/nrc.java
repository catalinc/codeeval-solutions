import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class nrc {

    public static char firstNonRepeatingCharacter(final String s) {
        final LinkedHashSet<Character> nonRepeating = new LinkedHashSet<Character>();
        final HashSet<Character> repeating = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (nonRepeating.contains(c)) {
                nonRepeating.remove(c);
                repeating.add(c);
            } else if (!repeating.contains(c)) {
                nonRepeating.add(c);
            }
        }
        if (nonRepeating.isEmpty()) {
            throw new RuntimeException("there are no non repeating characters in " + s);
        }
        return nonRepeating.iterator().next();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                out.println(firstNonRepeatingCharacter(line));
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
