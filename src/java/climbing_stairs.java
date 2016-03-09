import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class climbing_stairs {

    private static int countWaysToClimb(int numStairs) {
        if (numStairs < 0) {
            return 0;
        }
        if (numStairs == 0) {
            return 1;
        }
        return countWaysToClimb(numStairs - 1) + countWaysToClimb(numStairs - 2);
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextInt()) {
                int numStairs = scanner.nextInt();
                System.out.println(countWaysToClimb(numStairs));
            }
        }
    }

}
