import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class str_substitution {

    public static String replace(String s, LinkedHashMap<String, String> replacements) {
        LinkedList<Region> replacedRegionsSoFar = new LinkedList<Region>();
        for (Map.Entry<String, String> replacement : replacements.entrySet()) {
            String f = replacement.getKey();
            String r = replacement.getValue();
            s = replaceAll(s, f, r, replacedRegionsSoFar);
        }
        return s;
    }

    private static String replaceAll(String s, String f, String r, LinkedList<Region> replacedRegions) {
        int index = s.indexOf(f);
        while (index >= 0) {
            Region regionToReplace = new Region(index, index + r.length() - 1);
            boolean okToReplace = true;
            for (Region replacedRegion : replacedRegions) {
                if (regionToReplace.intersects(replacedRegion)) {
                    okToReplace = false;
                    break;
                }
            }
            if (okToReplace) {
                replacedRegions.add(regionToReplace);
                s = s.substring(0, index) + r + s.substring(index + f.length());
            }
            index = s.indexOf(f, index + f.length());
        }
        return s;
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
                String s = tokens[0];
                LinkedHashMap<String, String> replacements = parseReplacements(tokens[1]);
                out.println(replace(s, replacements));
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

    private static LinkedHashMap<String, String> parseReplacements(String s) {
        String[] tokens = s.split(",");
        LinkedHashMap<String, String> replacements =
                new LinkedHashMap<String, String>(tokens.length / 2);
        for (int i = 0; i < tokens.length - 1; i += 2) {
            replacements.put(tokens[i], tokens[i + 1]);
        }
        return replacements;
    }
}

class Region {
    final int start;
    final int end;

    Region(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Region other) {
        return (start <= other.start && end >= other.start) ||
                (other.start <= this.start && other.end >= start);
    }
}
