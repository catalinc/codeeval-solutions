import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PenultimateWord {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(penultimateWord(line));
            }
        }
    }

    private static String penultimateWord(String str) {
        String[] words = str.split(" ");
        return words[words.length - 2];
    }

}
