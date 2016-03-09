import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class longestlines {

    private static void insert(final String s, final List<String> strings, final int maxSize) {
        for (int i = 0; i < strings.size(); i++) {
            if (s.length() >= strings.get(i).length()) {
                strings.add(i, s);
                if (strings.size() > maxSize) {
                    strings.remove(strings.size() - 1);
                }
                return;
            }
        }
        if (strings.size() < maxSize) {
            strings.add(s);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + longestlines.class.getName() + " filename");
            System.exit(1);
        }
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            final List<String> longestLines = new LinkedList<String>();
            final int n = scanner.nextInt();
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine().trim();
                if (line.length() > 0) {
                    insert(line, longestLines, n);
                }
            }
            for (final String line : longestLines) {
                out.println(line);
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
