import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EvenNumbers {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                System.out.println((n & 1) == 1 ? 0 : 1);
            }
        }
    }

}
