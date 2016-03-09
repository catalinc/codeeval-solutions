import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SeatAllocation {

    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.length() > 0) {
                        boolean[][] graph = parseGraph(line);
                        int teamSize = graph[0].length;
                        int maxBPM = maxBipartiteMatching(graph);
                        if (maxBPM == teamSize) {
                            System.out.println("Yes");
                        } else {
                            System.out.println("No");
                        }
                    }
                }
            }
        }
    }

    private static boolean[][] parseGraph(String input) {
        String[] seatsAndPreferences = input.split(";\\s");
        int numSeats = Integer.parseInt(seatsAndPreferences[0]);
        String[] preferences = seatsAndPreferences[1].split("],\\s");
        boolean[][] graph = new boolean[numSeats][preferences.length];
        for (String preference : preferences) {
            String[] memberAndSeats = preference.split(":");
            int memberIndex = Integer.parseInt(memberAndSeats[0]) - 1;
            String seatPreferences = memberAndSeats[1];
            int index = seatPreferences.length();
            if (seatPreferences.endsWith("]")) {
                index = seatPreferences.length() - 1;
            }
            seatPreferences = seatPreferences.substring(1, index);
            String[] preferredSeats = seatPreferences.split(",\\s");
            for (String seat : preferredSeats) {
                int seatIndex = Integer.parseInt(seat) - 1;
                graph[seatIndex][memberIndex] = true;
            }
        }
        return graph;
    }

    private static int maxBipartiteMatching(boolean[][] graph) {
        int numSeats = graph.length;
        int teamSize = graph[0].length;

        int[] matches = new int[numSeats];
        Arrays.fill(matches, -1);

        int result = 0;
        for (int i = 0; i < teamSize; i++) {
            boolean[] visited = new boolean[numSeats];
            if (bpm(graph, i, visited, matches)) {
                result++;
            }
        }

        return result;
    }

    private static boolean bpm(boolean[][] graph, int i, boolean[] visited, int[] matches) {
        for (int j = 0; j < graph.length; j++) {
            if (graph[j][i] && !visited[j]) {
                visited[j] = true;
                if (matches[j] < 0 || bpm(graph, matches[j], visited, matches)) {
                    matches[j] = i;
                    return true;
                }
            }
        }
        return false;
    }

}
