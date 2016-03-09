import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class pascals_triangle {

    public static List<Integer> computePascalTriangle(int maxRow) {
        List<Integer> rows = new LinkedList<Integer>();
        for (int i = 0; i < maxRow; i++) {
            rows.addAll(computeRow(i));
        }
        return rows;
    }

    private static List<Integer> computeRow(int i) {
        LinkedList<Integer> values = new LinkedList<Integer>();
        int r = i + 1;
        int c = 1;
        values.add(1);
        while (c <= i) {
            int last = values.getLast();
            values.add((last * (r - c)) / c);
            c++;
        }
        return values;
    }

    private static String join(List<?> list, String sep) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Object[] items = list.toArray(new Object[list.size()]);
        for (int i = 0; i < items.length - 1; i++) {
            sb.append(items[i]);
            sb.append(sep);
        }
        sb.append(items[items.length - 1]);
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                List<Integer> triangleRows = computePascalTriangle(n);
                out.println(join(triangleRows, " "));
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
