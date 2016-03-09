import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sumint {

    public static int sumOfInts(String fileName) throws FileNotFoundException {
        int sum = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextInt()) {
                sum += scanner.nextInt();
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return sum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(sumOfInts(args[0]));
    }
}
