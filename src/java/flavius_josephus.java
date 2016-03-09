import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class flavius_josephus {

    public static List<Integer> executionOrder(int n, int m) {
        List<Integer> executed = new ArrayList<>(n);
        boolean[] dead = new boolean[n];
        int next = m - 1;
        while (true) {
            executed.add(next);
            dead[next] = true;
            if (executed.size() == n) {
                break;
            } else {
                int skipped = 0;
                while (skipped < m) {
                    next = (next + 1) % n;
                    if (!dead[next]) skipped++;
                }
            }
        }
        return executed;
    }

    private static int[] parseIntArray(String s) {
        String[] strings = s.split(",");
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.valueOf(strings[i]);
        }
        return ints;
    }

    private static String join(List<?> list, char sep) {
        StringBuilder sb = new StringBuilder();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size() - 1; i++) {
                sb.append(list.get(i));
                sb.append(sep);
            }
            sb.append(list.get(list.size() - 1));
        }
        return sb.toString();
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
                int n = arr[0];
                int m = arr[1];
                List<Integer> executed = executionOrder(n, m);
                out.println(join(executed, ' '));
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
