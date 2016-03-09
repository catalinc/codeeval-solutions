import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Solution for http://codeeval.com/open_challenges/42/
 */
public class ugly_numbers {

    private static final long[] ONE_DIGIT_PRIMES = {2, 3, 5, 7};

    private static boolean isUgly(final long n) {
        for (final long p : ONE_DIGIT_PRIMES) {
            if (n % p == 0) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> expressions;

    public static int countUglyNumbers(final String s) {
        expressions = new ArrayList<String>(s.length());
        genExpressions(s);
        int count = 0;
        for (final String e : expressions) {
            final long n = evaluateExpression(e);
            if (isUgly(n)) {
                count++;
            }
        }
        return count;
    }

    private static void genExpressions(final String s) {
        if (s.length() <= 1) {
            expressions.add(s);
            return;
        }

        final String head = s.substring(0, 1);
        final String tail = s.substring(1);
        genExpressions(tail);

        final List<String> expressionsSoFar = expressions;

        expressions = new ArrayList<String>(expressionsSoFar.size() * 3);
        for (final String e : expressionsSoFar) {
            expressions.add(head + e);
            expressions.add(head + "+" + e);
            expressions.add(head + "-" + e);
        }
    }

    private static final HashMap<Character, Long> charToNumber = new HashMap<Character, Long>(10);

    static {
        charToNumber.put('0', 0L);
        charToNumber.put('1', 1L);
        charToNumber.put('2', 2L);
        charToNumber.put('3', 3L);
        charToNumber.put('4', 4L);
        charToNumber.put('5', 5L);
        charToNumber.put('6', 6L);
        charToNumber.put('7', 7L);
        charToNumber.put('8', 8L);
        charToNumber.put('9', 9L);
    }

    private static long evaluateExpression(final String s) {
        long expressionValue = 0L;
        char operator = ' ';
        long currentNumber = 0L;
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            if (ch == '+' || ch == '-') {
                expressionValue = applyOperator(expressionValue, operator, currentNumber);
                operator = ch;
                currentNumber = 0L;
            } else {
                currentNumber = currentNumber * 10 + charToNumber.get(ch);
            }
        }
        expressionValue = applyOperator(expressionValue, operator, currentNumber);
        return expressionValue;
    }

    private static long applyOperator(long expressionValue,
                                      final char operator,
                                      final long currentNumber) {
        switch (operator) {
            case ' ':
                expressionValue = currentNumber;
                break;
            case '+':
                expressionValue = expressionValue + currentNumber;
                break;
            case '-':
                expressionValue = expressionValue - currentNumber;
                break;
            default:
                throw new RuntimeException("invalid operator " + operator);
        }
        return expressionValue;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + ugly_numbers.class.getName() + " filename");
            System.exit(1);
        }
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            String line;
            while ((line = in.readLine()) != null) {
                out.println(countUglyNumbers(line));
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

}
