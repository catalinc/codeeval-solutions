import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Solution for http://codeeval.com/open_challenges/7/
 */
public class prefix {

    private static final Map<String, Operator> OPERATOR_MAP;

    static {
        OPERATOR_MAP = new HashMap<String, Operator>();
        OPERATOR_MAP.put("+", new Operator() {
            public int apply(int left, int right) {
                return left + right;
            }
        });
        OPERATOR_MAP.put("*", new Operator() {
            public int apply(int left, int right) {
                return left * right;
            }
        });
        OPERATOR_MAP.put("/", new Operator() {
            public int apply(int left, int right) {
                return left / right;
            }
        });
    }

    public static int eval(final String s) {
        final Stack<Operator> operators = new Stack<Operator>();
        final LinkedList<Integer> numbers = new LinkedList<Integer>();
        final Scanner scanner = new Scanner(s);
        while (scanner.hasNext()) {
            final String x = scanner.next();
            if (OPERATOR_MAP.containsKey(x)) {
                operators.add(OPERATOR_MAP.get(x));
            } else {
                numbers.add(Integer.valueOf(x));
            }
        }
        while (!operators.isEmpty()) {
            final Operator op = operators.pop();
            final int left = numbers.remove(0);
            final int right = numbers.remove(0);
            numbers.add(0, op.apply(left, right));
        }
        return numbers.getFirst();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + prefix.class.getName() + " filename");
            System.exit(1);
        }
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            String line;
            while ((line = in.readLine()) != null) {
                out.println(eval(line));
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

interface Operator {
    int apply(int left, int right);
}
