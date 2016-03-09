import java.util.BitSet;
import java.util.LinkedList;

public class prime_palindrome {

    private static Integer[] digits(int n) {
        LinkedList<Integer> digits = new LinkedList<Integer>();
        while (n > 0) {
            digits.add(0, n % 10);
            n /= 10;
        }
        return digits.toArray(new Integer[digits.size()]);
    }

    private static boolean isPalindrome(int n) {
        Integer[] digits = digits(n);
        for (int i = 0, j = digits.length - 1; i < j; i++, j--) {
            if (!digits[i].equals(digits[j])) {
                return false;
            }
        }
        return true;
    }

    public static int biggestPrimePalindrome(int n) {
        BitSet primes = new BitSet(n + 1);
        for (int i = 2; i * i <= n; i = primes.nextClearBit(i + 1)) {
            for (int j = i + i; j <= n; j += i) {
                primes.set(j);
            }
        }
        int biggest = 2;
        for (int i = 3; i <= n; i = primes.nextClearBit(i + 1)) {
            if (isPalindrome(i)) {
                biggest = i;
            }
        }
        return biggest;
    }

    public static void main(String[] args) {
        System.out.println(biggestPrimePalindrome(1000));
    }
}
