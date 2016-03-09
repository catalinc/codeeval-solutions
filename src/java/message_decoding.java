import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class message_decoding {

    private static final int KEY_SIZE = 3;

    public static String decode(String s) {
        StringBuilder result = new StringBuilder();

        String header = parseHeader(s);
        int offset = header.length();
        int keyLength = parseKeyLength(offset, s);
        offset += KEY_SIZE;
        int endOfSegment = pow2(keyLength) - 1;
        while (keyLength > 0) {
            int key = parseKey(offset, keyLength, s);
            offset += keyLength;
            if (key == endOfSegment) {
                // end of segment
                keyLength = parseKeyLength(offset, s);
                offset += KEY_SIZE;
                endOfSegment = pow2(keyLength) - 1;
            } else {
                result.append(translateKey(header, key, keyLength));
            }
        }

        return result.toString();
    }

    private static String parseHeader(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != '0' && ch != '1') {
                sb.append(ch);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    private static int parseKeyLength(int offset, String s) {
        return Integer.valueOf(s.substring(offset, offset + KEY_SIZE), 2);
    }

    private static int parseKey(int offset, int keyLength, String s) {
        String keyStr = s.substring(offset, offset + keyLength);
        return Integer.valueOf(keyStr, 2);
    }

    private static char translateKey(String header, int key, int keyLength) {
        int offset = 0;
        for (int i = 1; i < keyLength; i++) {
            offset += pow2(i) - 1;
        }
        return header.charAt(offset + key);
    }

    private static int pow2(int n) {
        return 1 << n;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                out.println(decode(line));
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
