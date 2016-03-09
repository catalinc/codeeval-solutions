import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class balanced_smileys {

    public static boolean hasBalancedParentheses(String s) {
        int i = 0;
        int max = 0;
        int min = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    max++;
                    min++;
                    break;
                case ')':
                    max--;
                    if (max < 0) {
                        return false;
                    }
                    min--;
                    if (min < 0) {
                        min = 0;
                    }
                    break;
                case ':':
                    if (i < s.length() - 1) {
                        char n = s.charAt(i + 1);
                        if (n == '(') {
                            max++;
                            i++;
                        } else if (n == ')') {
                            min--;
                            if (min < 0) {
                                min = 0;
                            }
                            i++;
                        }
                    }
            }
            i++;
        }
        return min == 0 && max >= 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (hasBalancedParentheses(s)) {
                    out.println("YES");
                } else {
                    out.println("NO");
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
