import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DistinctSubsequences {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split(",");
                int n = countSubsequences(strings[0], strings[1]);
                System.out.println(n);
            }
        }
    }

    private static int countSubsequences(String s1, String s2) {
        int[] l = new int[s2.length() + 1];
        l[s2.length()] = 1;
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = 0; j < s2.length(); j++) {
                int c = s1.charAt(i) == s2.charAt(j) ? 1 : 0;
                l[j] += c * l[j + 1];
            }
        }
        return l[0];
    }

}
