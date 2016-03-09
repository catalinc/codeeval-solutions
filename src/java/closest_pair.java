import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Solution to http://codeeval.com/open_challenges/51/
 */
public class closest_pair {

    private static final int INFINITY = 10000;

    private static final Comparator<Point> X_AXIS_COMPARATOR = new Comparator<Point>() {
        public int compare(Point o1, Point o2) {
            if (o1.x > o2.x) {
                return 1;
            }
            return -1;
        }
    };

    private static final Comparator<Point> Y_AXIS_COMPARATOR = new Comparator<Point>() {
        public int compare(Point o1, Point o2) {
            if (o1.y > o2.y) {
                return 1;
            }
            return -1;
        }
    };

    private static double computeMinDistance(final List<Point> points) {
        Collections.sort(points, X_AXIS_COMPARATOR);
        return computeMinDistanceRecursive(points);
    }

    private static double computeMinDistanceRecursive(final List<Point> points) {
        if (points.size() <= 3) {
            return computeMinDistanceBruteForce(points);
        }

        final int mid = points.size() / 2;
        final List<Point> left = points.subList(0, mid);
        final List<Point> right = points.subList(mid, points.size());

        final double minDistLeft = computeMinDistanceRecursive(left);
        final double minDistRight = computeMinDistanceRecursive(right);
        double minDist = Math.min(minDistLeft, minDistRight);

        final double xMid = left.get(left.size() - 1).x;
        final List<Point> pointsInBandMinDist = new LinkedList<Point>();
        for (final Point p : points) {
            if (Math.abs(p.x - xMid) < minDist) {
                pointsInBandMinDist.add(p);
            }
        }
        Collections.sort(pointsInBandMinDist, Y_AXIS_COMPARATOR);
        for (int i = 0; i < pointsInBandMinDist.size(); i++) {
            final Point lowerPoint = pointsInBandMinDist.get(i);
            for (int j = i + 1; j < pointsInBandMinDist.size(); j++) {
                final Point upperPoint = pointsInBandMinDist.get(j);
                if (upperPoint.y - lowerPoint.y >= minDist) {
                    break;
                }
                final double distLowerUpper = distance(lowerPoint, upperPoint);
                if (distLowerUpper < minDist) {
                    minDist = distLowerUpper;
                }
            }
        }

        return minDist;
    }

    private static double computeMinDistanceBruteForce(final List<Point> points) {
        if (points.size() < 2) {
            return INFINITY;
        }
        double minDist = INFINITY;
        for (int i = 0; i < points.size(); i++) {
            final Point p = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                final Point q = points.get(j);
                final double dist = distance(p, q);
                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    private static double distance(final Point a, final Point b) {
        final double dx = a.x - b.x;
        final double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("usage: java " + closest_pair.class.getName() + " filename");
            System.exit(1);
        }
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            out = new PrintWriter(System.out);
            scanner = new Scanner(new FileReader(args[0]));
            while (true) {
                final int n = scanner.nextInt();
                if (n == 0) {
                    break;
                }
                final List<Point> points = new ArrayList<Point>(n);
                for (int i = 0; i < n; i++) {
                    final double x = scanner.nextDouble();
                    final double y = scanner.nextDouble();
                    final Point p = new Point(x, y);
                    points.add(p);
                }
                final double minDist = computeMinDistance(points);
                if (minDist >= INFINITY) {
                    out.println("INFINITY");
                } else {
                    out.printf("%.4f\n", minDist);
                }
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

    static class Point {
        double x;
        double y;

        Point(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
    }
}

