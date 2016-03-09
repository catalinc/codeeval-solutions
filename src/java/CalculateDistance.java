import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculateDistance {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Integer[] ints = parseInts(line);
                int x1 = ints[0];
                int y1 = ints[1];
                int x2 = ints[2];
                int y2 = ints[3];
                System.out.println(distance(x1, y1, x2, y2));
            }
        }
    }

    private static int distance(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    private static Integer[] parseInts(String s) {
        List<Integer> ints = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) || c == '-') {
                buffer.append(c);
            } else {
                if (buffer.length() > 0) {
                    ints.add(Integer.valueOf(buffer.toString()));
                    buffer = new StringBuilder();
                }
            }
        }
        if (buffer.length() > 0) {
            ints.add(Integer.valueOf(buffer.toString()));
        }
        return ints.toArray(new Integer[ints.size()]);
    }

}
