import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** Solution to https://www.codeeval.com/open_challenges/226/ */
public class TryToSolveIt {
    private static final Map<Character, Character> translationMap;

    static {
        translationMap = new HashMap<>();
        for (String mapping: new String[] {
                "au", "bv", "cw", "dx", "ey", "fz", "gn", "ho", "ip", "jq", "kr", "ls", "mt"}) {
            translationMap.put(mapping.charAt(0), mapping.charAt(1));
            translationMap.put(mapping.charAt(1), mapping.charAt(0));
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(decode(line));
            }
        }
    }

    private static String decode(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            char d = translationMap.get(c);
            sb.append(d);
        }
        return sb.toString();
    }
}
