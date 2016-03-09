import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/open_challenges/232/
 */
public final class NotSoClever {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int sepPos = line.indexOf('|');
                int[] array = parseIntArray(line.substring(0, sepPos - 1));
                int iterations = Integer.parseInt(line.substring(sepPos + 2));
                stupidSort(array, iterations);
                System.out.println(asString(array));
            }
        }
    }

    private static void stupidSort(int[] array, int iterations) {
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    break;
                }
            }
        }
    }

    private static String asString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    private static int[] parseIntArray(String s) {
        String[] tokens = s.split(" ");
        int[] array = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        return array;
    }
}
