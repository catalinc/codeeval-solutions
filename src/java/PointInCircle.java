import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PointInCircle {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Float[] floats = parseFloats(line);
                float cx = floats[0];
                float cy = floats[1];
                float r = floats[2];
                float x = floats[3];
                float y = floats[4];
                System.out.println(inCircle(cx, cy, r, x, y));
            }
        }
    }

    private static boolean inCircle(float cx, float cy, float r, float x, float y) {
        float dx = x - cx;
        float dy = y - cy;
        return dx * dx + dy * dy <= r * r;
    }

    private static Float[] parseFloats(String s) {
        List<Float> floats = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) || c == '-' || c == '.') {
                buffer.append(c);
            } else {
                if (buffer.length() > 0) {
                    floats.add(Float.valueOf(buffer.toString()));
                    buffer = new StringBuilder();
                }
            }
        }
        if (buffer.length() > 0) {
            floats.add(Float.valueOf(buffer.toString()));
        }
        return floats.toArray(new Float[floats.size()]);
    }

}
