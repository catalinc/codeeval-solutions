import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class sum4 {

    public static int numberOfWaysSumOfFourIsZero(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 3; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    for (int t = k + 1; t < arr.length; t++) {
                        if (arr[i] + arr[j] + arr[k] + arr[t] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private static int[] parseIntArray(String s) {
        String[] strings = s.split(",");
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.valueOf(strings[i]);
        }
        return ints;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                int[] arr = parseIntArray(s);
                out.println(numberOfWaysSumOfFourIsZero(arr));
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
