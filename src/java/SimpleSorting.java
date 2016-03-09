import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SimpleSorting {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                float[] numbers = parseFloats(scanner.nextLine());
                Arrays.sort(numbers);
                System.out.println(join(numbers, ' '));
            }
        }
    }

    private static float[] parseFloats(String str) {
        String[] tokens = str.split(" ");
        float[] numbers = new float[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = Float.parseFloat(tokens[i]);
        }
        return numbers;
    }

    private static String join(float[] numbers, char sep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length - 1; i++) {
            sb.append(format(numbers[i]));
            sb.append(sep);
        }
        sb.append(format(numbers[numbers.length - 1]));
        return sb.toString();
    }

    private static String format(float f) {
        return String.format("%.3f", f);
    }

}
