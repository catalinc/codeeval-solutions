import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class pangrams {

    public static List<Character> missingLetters(final String s) {
        final List<Character> missingLetters = new LinkedList<Character>();
        final Set<Integer> sentenceLetters = letters(s);
        for (int ch = 97; ch <= 122; ch++) {
            if (!sentenceLetters.contains(ch)) {
                missingLetters.add((char) ch);
            }
        }
        return missingLetters;
    }

    private static Set<Integer> letters(final String s) {
        final Set<Integer> letters = new HashSet<Integer>();
        for (int i = 0; i < s.length(); i++) {
            final char ch = Character.toLowerCase(s.charAt(i));
            if (isAsciiLetterLowerCase(ch)) {
                letters.add((int) ch);
            }
        }
        return letters;
    }

    private static boolean isAsciiLetterLowerCase(final char ch) {
        return ch >= 97 && ch <= 122;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                final String sentence = scanner.nextLine().trim();
                if (sentence.length() == 0) {
                    continue;
                }
                final List<Character> missingLetters = missingLetters(sentence);
                if (missingLetters.isEmpty()) {
                    out.println("NULL");
                } else {
                    out.println(join(missingLetters));
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

    private static String join(final List<Character> letters) {
        final StringBuilder sb = new StringBuilder();
        for (Character letter : letters) {
            sb.append(letter);
        }
        return sb.toString();
    }
}
