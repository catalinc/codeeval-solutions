import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Solution to https://www.codeeval.com/open_challenges/225/ */
public class Testing {
    private static final String SEP = " | ";

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(qa(line));
            }
        }
    }

    private static String qa(String str) {
        int sepPos = str.indexOf(SEP);
        int expectedStartPos = sepPos + 3;
        int bugs = 0;
        for (int i = 0; i < sepPos; i++) {
            char actual = str.charAt(i);
            char expected = str.charAt(expectedStartPos + i);
            if (actual != expected) bugs++;
        }
        if (bugs == 0) return "Done";
        if (bugs <= 2) return "Low";
        if (bugs <= 4) return "Medium";
        if (bugs <= 6) return "High";

        return "Critical";
    }
}
