import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class MarsNetworks {

    private static final class Edge implements Comparable<Edge> {
        private final int v;
        private final int u;
        private float weight;

        private Edge(int v, int u, float weight) {
            this.v = v;
            this.u = u;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight < o.weight) return -1;
            if (this.weight > o.weight) return 1;
            return 0;
        }
    }

    private static final class Graph {
        private int vertices;
        private List<Edge> edges;

        public Graph() {
            vertices = 0;
            edges = new ArrayList<>();
        }

        public float minimumSpanningTreeWeight() {
            Collections.sort(edges);
            Map<Integer, Set<Integer>> forest = new HashMap<>();
            for (int v = 0; v < vertices; v++) {
                Set<Integer> vs = new HashSet<>();
                vs.add(v);
                forest.put(v, vs);
            }
            List<Edge> minimumSpanningTree = new ArrayList<>();
            while (true) {
                Edge check = edges.remove(0);

                Set<Integer> visitedForU = forest.get(check.v);
                Set<Integer> visitedForV = forest.get(check.u);
                if (visitedForU.equals(visitedForV)) {
                    continue;
                }
                minimumSpanningTree.add(check);
                visitedForU.addAll(visitedForV);
                for (Integer i : visitedForU) {
                    forest.put(i, visitedForU);
                }
                if (visitedForU.size() == vertices) {
                    break;
                }
            }
            float totalWeight = 0f;
            for (Edge edge : minimumSpanningTree) {
                totalWeight += edge.weight;
            }
            return totalWeight;
        }
    }

    private static Graph buildGraph(String s) {
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        Scanner scanner = new Scanner(s).useDelimiter("[ ,]+");
        while (scanner.hasNextInt()) {
            xs.add(scanner.nextInt());
            ys.add(scanner.nextInt());
        }
        Graph graph = new Graph();
        for (int u = 0; u < xs.size() - 1; u++) {
            int xu = xs.get(u);
            int yu = ys.get(u);
            for (int v = u + 1; v < ys.size(); v++) {
                int xv = xs.get(v);
                int yv = ys.get(v);
                int dx = xu - xv;
                int dy = yu - yv;
                float weight = (float) Math.sqrt((dx * dx + dy * dy));
                graph.edges.add(new Edge(u, v, weight));
            }
        }
        graph.vertices = xs.size();
        return graph;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Graph graph = buildGraph(line);
                System.out.println((int)Math.ceil(graph.minimumSpanningTreeWeight()));
            }
        }
    }

}