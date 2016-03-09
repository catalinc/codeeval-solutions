import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class AromaticNumber {

    public static int parseInt(String s) {
        int value = 0;
        int previous = 0;
        for (int i = s.length() - 1; i >= 1; i -= 2) {
            int roman  = romanToInt(s.charAt(i));
            int arabic = arabicToInt(s.charAt(i - 1));
            int pairValue = arabic * roman;
            if (roman < previous) {
                value -= pairValue;
            } else {
                value += pairValue;
            }
            previous = roman;
        }
        return value;
    }

    private static int arabicToInt(char ch) {
        return Character.digit(ch, 10);
    }

    private static int romanToInt(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:
                throw new RuntimeException(String.format("invalid roman literal [%c]", ch));
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(parseInt(line));
            }
        }
    }

}
