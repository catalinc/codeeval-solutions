import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class beautiful_strings {

    public static int maxBeauty(String s) {
        int[] freq = letterFrequency(s);
        Arrays.sort(freq);

        int result = 0;
        for (int i = 1; i <= 26; i++) {
            result += i * freq[i - 1];
        }
        return result;
    }

    private static int[] letterFrequency(String s) {
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (c >= 'a' && c <= 'z') {
                freq[c - 'a']++;
            }
        }
        return freq;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                out.println(maxBeauty(s));
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
