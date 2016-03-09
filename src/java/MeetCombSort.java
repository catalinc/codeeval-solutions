import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solution to https://www.codeeval.com/open_challenges/233/
 */
public class MeetCombSort {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] a = parseIntArray(line);
                System.out.println(combSort(a, 1.25));
            }
        }
    }

    private static int combSort(int[] a, double decreaseFactor) {
        int iterCount = 0;
        int gap = a.length;
        while (!isSorted(a)) {
            if (gap > 1) {
                gap = (int) (gap / decreaseFactor);
            }
            for (int i = 0; i + gap < a.length; i++) {
                if (a[i] > a[i + gap]) {
                    int t = a[i];
                    a[i] = a[i + gap];
                    a[i + gap] = t;
                }
            }
            iterCount++;
        }
        return iterCount;
    }

    private static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) return false;
        }
        return true;
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