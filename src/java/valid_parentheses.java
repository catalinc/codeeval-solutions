import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class valid_parentheses {

    private static final Map<Character, Character> bracketPairs;

    static {
        bracketPairs = new HashMap<Character, Character>();
        bracketPairs.put(')', '(');
        bracketPairs.put(']', '[');
        bracketPairs.put('}', '{');
    }

    public static boolean isWellFormed(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (bracketPairs.containsValue(c)) {
                stack.push(c);
            } else if (bracketPairs.containsKey(c)) {
                if (bracketPairs.get(c) != stack.pop()) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (isWellFormed(s)) {
                    out.println("True");
                } else {
                    out.println("False");
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

}
