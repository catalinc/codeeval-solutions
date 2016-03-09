import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Skyscrapers {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> skyline = compute(line);
                System.out.println(join(skyline, ' '));
            }
        }
    }

    private static List<Integer> compute(String s) {
        List<Building> buildings = parseBuildings(s);
        int start = minStart(buildings);
        int end = maxEnd(buildings);
        List<Integer> skyline = new LinkedList<>();
        int lastHeight = 0;
        for (int i = start; i <= end; i++) {
            int height = maxHeight(i, buildings);
            if (height != lastHeight) {
                skyline.add(i);
                skyline.add(height);
            }
            lastHeight = height;
        }
        return skyline;
    }

    private static int minStart(List<Building> buildings) {
        int minStart = Integer.MAX_VALUE;
        for (int i = 0; i < buildings.size(); i++) {
            Building b = buildings.get(i);
            if (minStart > b.start) {
                minStart = b.start;
            }
        }
        return minStart;
    }

    private static int maxEnd(List<Building> buildings) {
        int maxEnd = Integer.MIN_VALUE;
        for (int i = 0; i < buildings.size(); i++) {
            Building b = buildings.get(i);
            if (maxEnd < b.end) {
                maxEnd = b.end;
            }
        }
        return maxEnd;
    }

    private static int maxHeight(int index, List<Building> buildings) {
        int maxHeight = 0;
        for (int i = 0; i < buildings.size(); i++) {
            Building b = buildings.get(i);
            if (b.start <= index && b.end > index) {
                if (b.height > maxHeight) {
                    maxHeight = b.height;
                }
            }
        }
        return maxHeight;
    }

    private static List<Building> parseBuildings(String s) {
        List<Building> buildings = new LinkedList<>();
        Scanner scanner = new Scanner(s).useDelimiter("[ \\(\\);,]+");
        while (scanner.hasNextInt()) {
            int start = scanner.nextInt();
            int height = scanner.nextInt();
            int end = scanner.nextInt();
            buildings.add(new Building(start, height, end));
        }
        return buildings;
    }

    private static String join(List<Integer> ints, char sep) {
        StringBuilder sb = new StringBuilder();
        int lastIndex = ints.size() - 1;
        for (int i = 0; i < lastIndex; i++) {
            sb.append(ints.get(i));
            sb.append(sep);
        }
        sb.append(ints.get(lastIndex));
        return sb.toString();
    }

    private static class Building {
        private final int start;
        private final int height;
        private final int end;

        private Building(int start, int height, int end) {
            this.start = start;
            this.height = height;
            this.end = end;
        }
    }

}