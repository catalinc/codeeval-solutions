import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class int_palindrome {

    public static int countInterestingRanges(int left, int right) {
        int size = right - left + 1;
        BitSet palindromes = new BitSet(size);
        for (int i = left; i <= right; i++) {
            if (isPalindrome(i)) {
                palindromes.set(i - left);
            }
        }
        return countRangesWithEvenNumberOfSetBits(palindromes, size);
    }

    private static int countRangesWithEvenNumberOfSetBits(BitSet bs, int size) {
        int rangeCount = 0;
        for (int i = 0; i < size; i++) {
            if (!bs.get(i)) {
                rangeCount++;
            }
            for (int j = i + 1; j < size; j++) {
                if (countSetBits(bs, i, j) % 2 == 0) {
                    rangeCount++;
                }
            }
        }
        return rangeCount;
    }

    private static int countSetBits(BitSet bs, int start, int end) {
        int bitCount = 0;
        for (int i = bs.nextSetBit(start); i >= 0 && i <= end; i = bs.nextSetBit(i + 1)) {
            bitCount++;
        }
        return bitCount;
    }

    private static ArrayList<Integer> digits(int n) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while (n > 0) {
            digits.add(0, n % 10);
            n /= 10;
        }
        return digits;
    }

    private static boolean isPalindrome(int n) {
        if (n < 10) {
            return true;
        }
        ArrayList<Integer> digits = digits(n);
        for (int i = 0, j = digits.size() - 1; i < j; i++, j--) {
            if (!digits.get(i).equals(digits.get(j))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextInt()) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                out.println(countInterestingRanges(left, right));
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

}
