import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DecodeNumbers {

    private static int countWaysToDecode(String str) {
        int count = 1;
        for (int i = 0; i < str.length() - 1; i++) {
            int n = Integer.parseInt(str.substring(i, i + 2));
            if (n >= 1 && n <= 26) count++;
        }
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                int count = countWaysToDecode(scanner.nextLine());
                System.out.println(count);
            }
        }
    }

}
