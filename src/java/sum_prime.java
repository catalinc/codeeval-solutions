import java.util.LinkedList;

public class sum_prime {

    public static int sumPrimes(int n) {
        return sum(primes(n));
    }

    private static int sum(Iterable<Integer> values) {
        int sum = 0;
        for (Integer i : values) {
            sum += i;
        }
        return sum;
    }

    private static LinkedList<Integer> primes(int max) {
        LinkedList<Integer> primes = new LinkedList<Integer>();
        primes.add(2);
        primes.add(3);
        while (primes.size() < max) {
            int nextPrime = primes.get(primes.size() - 1) + 2;
            while (true) {
                boolean isPrime = true;
                for (Integer prime : primes) {
                    if (nextPrime % prime == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    break;
                }
                nextPrime += 2;
            }
            primes.add(nextPrime);
        }
        return primes;
    }

    public static void main(String[] args) {
        System.out.println(sumPrimes(1000));
    }

}
