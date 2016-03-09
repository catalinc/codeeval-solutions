import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SeqTransform {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (canTransform(line)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }
    }

    private static boolean canTransform(String line) {
        String[] patternAndSequence = line.split(" ");
        String pattern = patternAndSequence[0];
        String sequence = patternAndSequence[1];

        // FIXME
        boolean[][] matrix = new boolean[pattern.length()][sequence.length()];
        for (int i = 0; i < pattern.length(); i++) {
            for (int j = 0; j < sequence.length(); j++) {
                if (i == 0 || j == 0 || matrix[i - 1][j - 1]) {
                    matrix[i][j] = (pattern.charAt(i) == '0' && sequence.charAt(j) == 'A') || pattern.charAt(i) == '1';
                } else {
                    matrix[i][j] = matrix[i - 1][j] && sequence.charAt(j - 1) == sequence.charAt(j);
                }
            }

        }
        return matrix[pattern.length() - 1][sequence.length() - 1];
    }
}