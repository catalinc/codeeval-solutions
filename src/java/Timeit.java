public class Timeit {
    private static long start;

    public static void markStart() {
        start = System.nanoTime();
    }

    public static void printElapsed() {
        System.out.println((System.nanoTime() - start) / 1000000.0  + " ms");
    }
}
