import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class lca {

    static class Node {
        int value;
        Node left;
        Node right;

        Node(final int value) {
            this.value = value;
        }

    }

    static class BST {
        Node root;

        void insert(final int value) {
            if (root == null) {
                root = new Node(value);
            } else {
                Node crt = root;
                while (true) {
                    if (value >= crt.value) {
                        if (crt.right != null) {
                            crt = crt.right;
                        } else {
                            crt.right = new Node(value);
                            return;
                        }
                    } else {
                        if (crt.left != null) {
                            crt = crt.left;
                        } else {
                            crt.left = new Node(value);
                            return;
                        }
                    }
                }
            }
        }

        int lca(final int value1, final int value2) {
            Node crt = root;
            while (crt != null) {
                if (value1 < crt.value && value2 < crt.value) {
                    crt = crt.left;
                } else if (value1 > crt.value && value2 > crt.value) {
                    crt = crt.right;
                } else {
                    return crt.value;
                }
            }
            throw new RuntimeException("lowest common ancestor for (" + value1 + "," + value2 + ") not found");
        }
    }

    private static final BST bst;

    static {
        bst = new BST();
        for (final int value : new int[]{30, 8, 52, 3, 20, 10, 29}) {
            bst.insert(value);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + lca.class.getName() + " filename");
            System.exit(1);
        }
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            while (in.hasNextInt()) {
                final int value1 = in.nextInt();
                final int value2 = in.nextInt();
                out.println(bst.lca(value1, value2));
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
