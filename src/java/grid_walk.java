import java.io.IOException;
import java.util.*;

public class grid_walk {

    private static boolean canWalkOn(final int x, final int y) {
        final int sum = sumOfDigits(x) + sumOfDigits(y);
        return sum <= 19;
    }

    private static int sumOfDigits(int n) {
        int sum = 0;
        n = Math.abs(n);
        while (n > 0) {
            sum = sum + (n % 10);
            n = n / 10;
        }
        return sum;
    }

    private static final int[] OFFSETS = {-1, 1};

    private static List<Point> walkableNeighbours(final Point p) {
        final List<Point> points = new ArrayList<>(4);
        for (final int dx : OFFSETS) {
            final int x = p.x + dx;
            if (canWalkOn(x, p.y)) {
                points.add(new Point(x, p.y));
            }
        }
        for (final int dy : OFFSETS) {
            final int y = p.y + dy;
            if (canWalkOn(p.x, y)) {
                points.add(new Point(p.x, y));
            }
        }
        return points;
    }

    private static int gridWalk() {
        final Set<Point> explored = new HashSet<>();
        final Stack<Point> toExplore = new Stack<>();
        toExplore.push(new Point(0, 0));
        while (!toExplore.isEmpty()) {
            final Point point = toExplore.pop();
            explored.add(point);
            for (final Point neighbour : walkableNeighbours(point)) {
                if (!explored.contains(neighbour)) {
                    toExplore.push(neighbour);
                }
            }
        }
        return explored.size();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(gridWalk());
    }

    static class Point {
        final int x;
        final int y;
        
        final static Map<Integer, Point> cache = new HashMap<>();

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
        
        public static Point at(final int x, final int y) {
           int key = Objects.hash(x, y);
           Point point = cache.get(key);
           if (point == null) {
               point = new Point(x, y);
               cache.put(key, point);
           }
           return point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }        
    }

}

