import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CapitalizeWords {

    public static String capitalizeWords(String s) {
        char[] chars = s.toCharArray();
        boolean isFirst = true;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isSpaceChar(chars[i])) {
                isFirst = true;
            } else if (isFirst) {
                chars[i] = Character.toUpperCase(chars[i]);
                isFirst = false;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String sentence = scanner.nextLine();
                System.out.println(capitalizeWords(sentence));
            }
        }
    }

}
