import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class overlapping_rectangles {

    public static class Rectangle {
        int left;
        int top;
        int right;
        int bottom;

        public Rectangle(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }

    public static List<Rectangle> parseRectangles(String s) {
        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        int[] coordinates = parseIntArray(s);
        for (int i = 0; i < coordinates.length; i += 4) {
            Rectangle r = new Rectangle(coordinates[i], coordinates[i + 1], coordinates[i + 2], coordinates[i + 3]);
            rectangles.add(r);
        }
        return rectangles;
    }

    private static int[] parseIntArray(String s) {
        String[] strings = s.split(",");
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.valueOf(strings[i]);
        }
        return ints;
    }

    public static boolean overlap(Rectangle r1, Rectangle r2) {
        return overlapOnXAxis(r1, r2) && overlapOnYAxis(r1, r2);
    }

    private static boolean overlapOnXAxis(Rectangle r1, Rectangle r2) {
        return !(r1.right < r2.left || r1.left > r2.right);
    }

    private static boolean overlapOnYAxis(Rectangle r1, Rectangle r2) {
        return !(r1.top < r2.bottom || r1.bottom > r2.top);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                List<Rectangle> rectangles = parseRectangles(s);
                if (overlap(rectangles.get(0), rectangles.get(1))) {
                    out.println("True");
                } else {
                    out.println("False");
                }
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


