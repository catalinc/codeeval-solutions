import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ColorCodeConverter {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String testCase = scanner.nextLine();
                System.out.println(toRGB(testCase));
            }
        }
    }

    private static String toRGB(String str) {
        float r = 0, g = 0, b = 0;
        if (str.startsWith("HSL")) {
            float[] components = parseFloatArray(str.substring(3));
            float h = components[0];
            float s = components[1] / 100;
            float l = components[2] / 100;

            float c = (1 - Math.abs(2 * l - 1)) * s;
            float x = c * (1 - Math.abs((h / 60) % 2 - 1));
            float m = l - c / 2;

            float rp, gp, bp;
            if (h < 60) {
                rp = c;
                gp = x;
                bp = 0;
            } else if (h >= 60 && h < 120) {
                rp = x;
                gp = c;
                bp = 0;
            } else if (h >= 120 && h < 180) {
                rp = 0;
                gp = c;
                bp = x;
            } else if (h >= 180 && h < 240) {
                rp = 0;
                gp = x;
                bp = c;
            } else if (h >= 240 && h < 300) {
                rp = x;
                gp = 0;
                bp = c;
            } else {
                rp = c;
                gp = 0;
                bp = x;
            }

            r = (rp + m) * 255;
            g = (gp + m) * 255;
            b = (bp + m) * 255;
        } else if (str.startsWith("HSV")) {
            float[] components = parseFloatArray(str.substring(3));
            float h = components[0];
            float s = components[1] / 100;
            float v = components[2] / 100;

            float c = v * s;
            float x = c * (1 - Math.abs((h / 60) % 2 - 1));
            float m = v - c;

            float rp, gp, bp;
            if (h < 60) {
                rp = c;
                gp = x;
                bp = 0;
            } else if (h >= 60 && h < 120) {
                rp = x;
                gp = c;
                bp = 0;
            } else if (h >= 120 && h < 180) {
                rp = 0;
                gp = c;
                bp = x;
            } else if (h >= 180 && h < 240) {
                rp = 0;
                gp = x;
                bp = c;
            } else if (h >= 240 && h < 300) {
                rp = x;
                gp = 0;
                bp = c;
            } else {
                rp = c;
                gp = 0;
                bp = x;
            }

            r = (rp + m) * 255;
            g = (gp + m) * 255;
            b = (bp + m) * 255;
        } else if (str.startsWith("(")) {
            float[] components = parseFloatArray(str);
            float c = components[0];
            float m = components[1];
            float y = components[2];
            float k = components[3];

            r = 255 * (1 - c) * (1 - k);
            g = 255 * (1 - m) * (1 - k);
            b = 255 * (1 - y) * (1 - k);
        } else if (str.startsWith("#")) {
            r = Integer.parseInt(str.substring(1, 3), 16);
            g = Integer.parseInt(str.substring(3, 5), 16);
            b = Integer.parseInt(str.substring(5), 16);
        }
        return String.format("RGB(%d,%d,%d)", Math.round(r), Math.round(g), Math.round(b));
    }

    private static float[] parseFloatArray(String s) {
        String[] splits = s.substring(1, s.length() - 1).split(",");
        float[] floats = new float[splits.length];
        for (int i = 0; i < splits.length; i++) {
            floats[i] = Float.parseFloat(splits[i]);
        }
        return floats;
    }

}
