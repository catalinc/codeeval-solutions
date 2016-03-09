import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DaVyncy {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String testCase = scanner.nextLine();
                List<String> fragments = new LinkedList<>(Arrays.asList(testCase.split(";")));
                System.out.println(reassemble(fragments));
            }
        }
    }

    private static String reassemble(List<String> fragments) {
        while (fragments.size() > 1) {
            Overlap maxOverlap = Overlap.NONE;
            for (int i = 0; i < fragments.size() - 1; i++) {
                for (int j = i + 1; j < fragments.size(); j++) {
                    String si = fragments.get(i);
                    String sj = fragments.get(j);
                    Overlap overlap = computeMaximalOverlap(si, sj);
                    if (overlap.length > maxOverlap.length) {
                        maxOverlap = overlap;
                    }
                }
            }
            if (maxOverlap == Overlap.NONE) break;
            fragments.remove(maxOverlap.first);
            fragments.remove(maxOverlap.second);
            fragments.add(maxOverlap.result);
        }

        String longest = fragments.get(0);
        for (int i = 1; i < fragments.size(); i++) {
            String s = fragments.get(i);
            if (s.length() > longest.length()) {
                longest = s;
            }
        }
        return longest;
    }

    private static Overlap computeMaximalOverlap(String s1, String s2) {
        Overlap o1 = computeOverlap(s1, s2);
        Overlap o2 = computeOverlap(s2, s1);
        return o1.length > o2.length ? o1 : o2;
    }

    private static Overlap computeOverlap(String first, String second) {
        for (int i = second.length(); i > 0; i--) {
            String overlap = second.substring(0, i);
            if (first.endsWith(overlap)) {
                return new Overlap(first, second, first + second.substring(i), overlap.length());
            }
        }
        return Overlap.NONE;
    }

    private static class Overlap {
        private String first;
        private String second;
        private String result;
        private int length;

        private Overlap(String first, String second, String result, int length) {
            this.first = first;
            this.second = second;
            this.result = result;
            this.length = length;
        }

        private static Overlap NONE = new Overlap(null, null, null, 0);
    }

}
