import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class string_rotation {

    public static boolean isRotation(String a, String b) {
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) == a.charAt(0)) {
                if ((b.substring(i) + b.substring(0, i)).equals(a)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(args[0]));
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final String[] strings = line.split(",");
                if (isRotation(strings[0], strings[1])) {
                    System.out.println("True");
                } else {
                    System.out.println("False");
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

}
