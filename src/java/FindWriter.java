import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindWriter {

    public static String decode(String s) {
        String[] tokens = s.split("\\|");
        String text = tokens[0];
        String key = tokens[1];
        Scanner scanner = new Scanner(key);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextInt()) {
            int index = scanner.nextInt() - 1;
            char ch = text.charAt(index);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(decode(line));
            }
        }
    }

}
