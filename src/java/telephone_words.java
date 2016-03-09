import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class telephone_words {

    private static final char[][] KEYPAD;

    static {
        KEYPAD = new char[10][];
        KEYPAD[0] = new char[]{'0'};
        KEYPAD[1] = new char[]{'1'};
        KEYPAD[2] = "abc".toCharArray();
        KEYPAD[3] = "def".toCharArray();
        KEYPAD[4] = "ghi".toCharArray();
        KEYPAD[5] = "jkl".toCharArray();
        KEYPAD[6] = "mno".toCharArray();
        KEYPAD[7] = "pqrs".toCharArray();
        KEYPAD[8] = "tuv".toCharArray();
        KEYPAD[9] = "wxyz".toCharArray();
    }

    private static final int LIST_SIZE = 16384; // 4**7

    public static ArrayList<String> telephoneNumberToWords(String telephoneNumber) {
        ArrayList<String> result = new ArrayList<String>(LIST_SIZE);
        int[] digits = digits(telephoneNumber);
        for (char c0 : KEYPAD[digits[0]]) {
            for (char c1 : KEYPAD[digits[1]]) {
                for (char c2 : KEYPAD[digits[2]]) {
                    for (char c3 : KEYPAD[digits[3]]) {
                        for (char c4 : KEYPAD[digits[4]]) {
                            for (char c5 : KEYPAD[digits[5]]) {
                                for (char c6 : KEYPAD[digits[6]]) {
                                    result.add(new String(new char[]{c0, c1, c2, c3, c4, c5, c6}));
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private static int[] digits(String s) {
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = Character.digit(s.charAt(i), 10);
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String telephoneNumber = scanner.nextLine();
                List<String> words = telephoneNumberToWords(telephoneNumber);
                Collections.sort(words);
                out.println(join(words, ','));
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

    private static String join(List<String> strings, char sep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(strings.get(i));
            if (i != strings.size() - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

}
