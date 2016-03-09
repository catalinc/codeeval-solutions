import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class count_primes {

    public static Integer[] sieve(int max) {
        BitSet bs = new BitSet(max + 1);
        for (int p = 2; p * p <= max; p = bs.nextClearBit(p + 1)) {
            for (int i = p * p; i <= max; i += p) {
                bs.set(i);
            }
        }

        List<Integer> primes = new LinkedList<Integer>();
        for (int p = 2; p <= max; p = bs.nextClearBit(p + 1)) {
            primes.add(p);
        }
        return primes.toArray(new Integer[primes.size()]);
    }

    public static class Interval {
        final int lo;
        final int hi;

        Interval(final int lo, final int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }

    public static void solve(List<Interval> intervals, PrintWriter out) {
        int max = Integer.MIN_VALUE;
        for (Interval interval : intervals) {
            if (max < interval.hi) max = interval.hi;
        }

        Integer[] primes = sieve(max);

        for (Interval interval : intervals) {
            out.println(countPrimesInInterval(interval, primes));
        }
    }

    private static int countPrimesInInterval(Interval interval, Integer[] primes) {
        for (int i = 0; i < primes.length; i++) {
            if (primes[i] >= interval.lo) {
                int count = 0;
                for (int j = i; j < primes.length; j++) {
                    if (primes[j] > interval.hi) break;
                    count++;
                }
                return count;
            }
            if (primes[i] > interval.hi) break;
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        List<Interval> intervals = new LinkedList<Interval>();
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int lo = Integer.parseInt(parts[0]);
                int hi = Integer.parseInt(parts[1]);
                intervals.add(new Interval(lo, hi));
            }
            solve(intervals, out);
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
