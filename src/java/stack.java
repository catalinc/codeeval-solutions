import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class stack {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner lineScanner = null;
        PrintWriter out = null;
        try {
            lineScanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            final IntStack stack = new IntStack(32);
            while (lineScanner.hasNextLine()) {
                final String line = lineScanner.nextLine();
                final Scanner intScanner = new Scanner(line);
                while (intScanner.hasNextInt()) {
                    stack.push(intScanner.nextInt());
                }
                final StringBuilder sb = new StringBuilder();
                boolean collect = true;
                while (!stack.isEmpty()) {
                    final int n = stack.pop();
                    if (collect) {
                        sb.append(n);
                        sb.append(' ');
                    }
                    collect = !collect;
                }
                final String s = sb.toString().trim();
                out.println(s);
            }
        } finally {
            if (lineScanner != null) {
                lineScanner.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

}

class IntStack {
    private int size;
    private int[] data;

    IntStack(final int initialCapacity) {
        data = new int[initialCapacity];
        size = 0;
    }

    void push(final int value) {
        if (size == data.length) {
            final int[] a = new int[data.length * 2];
            System.arraycopy(data, 0, a, 0, data.length);
            data = a;
        }
        data[size] = value;
        size++;
    }

    int pop() {
        size--;
        return data[size];
    }

    boolean isEmpty() {
        return size == 0;
    }

}
