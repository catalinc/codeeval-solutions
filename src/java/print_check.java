import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class print_check {

    private static final SortedMap<Integer, String> NUMBER_TO_TEXT;

    static {
        NUMBER_TO_TEXT = new TreeMap<Integer, String>(Collections.reverseOrder());
        NUMBER_TO_TEXT.put(0, "Zero");
        NUMBER_TO_TEXT.put(1, "One");
        NUMBER_TO_TEXT.put(2, "Two");
        NUMBER_TO_TEXT.put(3, "Three");
        NUMBER_TO_TEXT.put(4, "Four");
        NUMBER_TO_TEXT.put(5, "Five");
        NUMBER_TO_TEXT.put(6, "Six");
        NUMBER_TO_TEXT.put(7, "Seven");
        NUMBER_TO_TEXT.put(8, "Eight");
        NUMBER_TO_TEXT.put(9, "Nine");
        NUMBER_TO_TEXT.put(10, "Ten");
        NUMBER_TO_TEXT.put(11, "Eleven");
        NUMBER_TO_TEXT.put(12, "Twelve");
        NUMBER_TO_TEXT.put(13, "Thirteen");
        NUMBER_TO_TEXT.put(14, "Fourteen");
        NUMBER_TO_TEXT.put(15, "Fifteen");
        NUMBER_TO_TEXT.put(16, "Sixteen");
        NUMBER_TO_TEXT.put(17, "Seventeen");
        NUMBER_TO_TEXT.put(18, "Eighteen");
        NUMBER_TO_TEXT.put(19, "Nineteen");
        NUMBER_TO_TEXT.put(20, "Twenty");
        NUMBER_TO_TEXT.put(30, "Thirty");
        NUMBER_TO_TEXT.put(40, "Forty");
        NUMBER_TO_TEXT.put(50, "Fifty");
        NUMBER_TO_TEXT.put(60, "Sixty");
        NUMBER_TO_TEXT.put(70, "Seventy");
        NUMBER_TO_TEXT.put(80, "Eighty");
        NUMBER_TO_TEXT.put(90, "Ninety");
        NUMBER_TO_TEXT.put(100, "Hundred");
        NUMBER_TO_TEXT.put(1000, "Thousand");
        NUMBER_TO_TEXT.put(1000000, "Million");
        NUMBER_TO_TEXT.put(1000000000, "Billion");
    }

    public static String numberToText(int n) {
        StringBuilder sb = new StringBuilder();
        do {
            for (Map.Entry<Integer, String> entry : NUMBER_TO_TEXT.entrySet()) {
                int unit = entry.getKey();
                if (n >= unit) {
                    String text = entry.getValue();
                    if (n < 100) {
                        sb.append(text);
                        n -= unit;
                    } else {
                        int order = n / unit;
                        sb.append(numberToText(order)).append(text);
                        n -= order * unit;
                    }
                    break;
                }
            }
        } while (n > 0);
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
                out.println(numberToText(n) + "Dollars");
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
