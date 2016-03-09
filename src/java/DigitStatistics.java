import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DigitStatistics {

    private static int[] lastDigitTable = {
            0, 0, 0, 0, 1, 1, 1, 1, 6, 2, 4, 8, 1, 3, 9, 7, 6, 4, 6, 4,
            5, 5, 5, 5, 6, 6, 6, 6, 1, 7, 9, 3, 6, 8, 4, 2, 1, 9, 1, 9
    };

    private static int[] frequency = new int[10];

    private static void computeFrequencyForLastDigit(int a, long n) {
        Arrays.fill(frequency, 0);
        for (long i = 1; i <= n; i++) {
            int lastDigit = lastDigitTable[((int) (((a % 10) * 4) + (i % 4)))];
            frequency[lastDigit]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < frequency.length; i++) {
            sb.append(i);
            sb.append(": ");
            sb.append(frequency[i]);
            if (i != frequency.length - 1) {
                sb.append(", ");
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                int a = Integer.parseInt(tokens[0]);
                long n = Long.parseLong(tokens[1]);
                computeFrequencyForLastDigit(a, n);
            }
        }
    }
}
