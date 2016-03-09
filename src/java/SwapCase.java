import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SwapCase {

    public static String swapCase(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    chars[i] = Character.toLowerCase(c);
                } else {
                    chars[i] = Character.toUpperCase(c);
                }
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String sentence = scanner.nextLine();
                System.out.println(swapCase(sentence));
            }
        }
    }

}
