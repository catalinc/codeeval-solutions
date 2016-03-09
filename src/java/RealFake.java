import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** Solution to https://www.codeeval.com/open_challenges/227/ */
public final class RealFake {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(isReal(line) ? "Real" : "Fake");
            }
        }

    }

    private static boolean isReal(String s) {
        int sum = 0, count = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            count++;
            int d = digit(c);
            if ((count & 1) == 0) {
                d += d;
            }
            sum += d;
        }
        return sum % 10 == 0;
    }

    private static int digit(char c) {
        return c - '0';
    }
}
