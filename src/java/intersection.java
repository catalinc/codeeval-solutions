import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class intersection {

    public static List<Integer> intersect(List<Integer> l1, List<Integer> l2) {
        List<Integer> result = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        while (i < l1.size() && j < l2.size()) {
            int n1 = l1.get(i);
            int n2 = l2.get(j);
            if (n1 < n2) {
                i++;
            } else if (n1 > n2) {
                j++;
            } else {
                result.add(n1);
                i++;
                j++;
            }
        }
        return result;
    }

    private static List<Integer> parseNumbers(String s) {
        List<Integer> numbers = new LinkedList<Integer>();
        String[] tokens = s.split(",");
        for (String token : tokens) {
            numbers.add(Integer.valueOf(token));
        }
        return numbers;
    }

    private static String join(List<Integer> numbers, char sep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            sb.append(numbers.get(i));
            if (i != numbers.size() - 1) {
                sb.append(sep);
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
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(";");
                List<Integer> l1 = parseNumbers(tokens[0]);
                List<Integer> l2 = parseNumbers(tokens[1]);
                out.println(join(intersect(l1, l2), ','));
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
