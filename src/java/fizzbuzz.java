import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class fizzbuzz {

    public static String playFizzBuzz(int a, int b, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int remA = i % a;
            int remB = i % b;
            if (remA == 0 && remB == 0) {
                sb.append("FB");
            } else if (remA == 0) {
                sb.append('F');
            } else if (remB == 0) {
                sb.append('B');
            } else {
                sb.append(i);
            }
            if (i != n) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int n = scanner.nextInt();
                out.println(playFizzBuzz(a, b, n));
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
