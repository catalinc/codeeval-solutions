import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/open_challenges/173/
 */
public class WithoutRepetitions {

    private static String deleteRepeated(String s) {
        if (s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != sb.charAt(sb.length() - 1)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(deleteRepeated(s));
            }
        }
    }

}
