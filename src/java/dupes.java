import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class dupes {

    public static List<Integer> removeDupes(List<Integer> numbers) {
        List<Integer> result = new LinkedList<Integer>();
        result.add(numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            int previous = numbers.get(i - 1);
            int current = numbers.get(i);
            if (current != previous) {
                result.add(current);
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
                List<Integer> numbers = parseNumbers(line);
                out.println(join(removeDupes(numbers), ','));
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
