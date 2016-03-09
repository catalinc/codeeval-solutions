import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlayWithDna {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String testCase = scanner.nextLine();
                String[] splits = testCase.split(" ");
                String segment = splits[0];
                int maxAllowedMismatches = Integer.parseInt(splits[1]);
                String dna = splits[2];
                List<DnaMatch> matches = findMatches(segment, dna, maxAllowedMismatches);
                if (matches.isEmpty()) {
                    System.out.println("No match");
                } else {
                    Collections.sort(matches);
                    System.out.println(join(matches, ' '));
                }
            }
        }
    }

    private static String join(List<DnaMatch> matches, char sep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matches.size(); i++) {
            sb.append(matches.get(i).match);
            if (i != matches.size() - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

    private static List<DnaMatch> findMatches(String segment, String dna, int maxAllowedMismatches) {
        List<DnaMatch> matches = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < segment.length() - 1; i++) {
            buffer.append(dna.charAt(i));
        }
        for (int i = segment.length() - 1; i < dna.length(); i++) {
            buffer.append(dna.charAt(i));
            int distance = levenshteinDistance(segment, buffer.toString());
            if (distance <= maxAllowedMismatches) {
                matches.add(new DnaMatch(buffer.toString(), distance));
            }
            buffer.deleteCharAt(0);
        }
        return matches;
    }

    private static int levenshteinDistance(String s, String t) {
        if (s.equals(t)) {
            return 0;
        }
        if (s.isEmpty()) {
            return t.length();
        }
        if (t.isEmpty()) {
            return s.length();
        }

        int[] v0 = new int[t.length() + 1];
        int[] v1 = new int[t.length() + 1];

        for (int i = 0; i < t.length(); i++) {
            v0[i] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            v1[0] = i + 1;

            for (int j = 0; j < t.length(); j++) {
                int cost = s.charAt(i) == t.charAt(j) ? 0 : 1;
                v1[j + 1] = min(v1[j] + 1, v0[j + 1] + 1, v0[j] + cost);
            }

            System.arraycopy(v1, 0, v0, 0, v1.length);
        }

        return v1[t.length()];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private static final class DnaMatch implements Comparable<DnaMatch> {
        private final String match;
        private final int distance;

        private DnaMatch(String match, int distance) {
            this.match = match;
            this.distance = distance;
        }

        @Override
        public int compareTo(DnaMatch o) {
            if (this.distance > o.distance) {
                return 1;
            }
            if (this.distance < o.distance) {
                return -1;
            }
            return this.match.compareTo(o.match);
        }
    }
}
