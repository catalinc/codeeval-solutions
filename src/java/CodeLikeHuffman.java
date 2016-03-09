import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class CodeLikeHuffman {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Encoding> encodings = encode(line);
                String output = encodings.stream().map(Encoding::toString).collect(Collectors.joining("; "));
                System.out.println(output);
            }
        }
    }

    private static List<Encoding> encode(String text) {
        Map<Character, Node> characterFrequencyMap = buildCharacterFrequencyMap(text);
        Node root = buildHuffmanTree(characterFrequencyMap);
        return buildEncodings(root);
    }

    private static Map<Character, Node> buildCharacterFrequencyMap(String text) {
        Map<Character, Node> charFreq = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            Node n = charFreq.get(c);
            if (n == null) {
                n = new Node(c, 0);
                charFreq.put(c, n);
            }
            n.freq++;
        }
        return charFreq;
    }

    private static Node buildHuffmanTree(Map<Character, Node> charFreq) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.addAll(charFreq.values());
        while (queue.size() > 1) {
            Node left = queue.remove();
            Node right = queue.remove();
            Node n = new Node('*', left.freq + right.freq, left, right);
            queue.add(n);
        }
        return queue.remove();
    }

    private static List<Encoding> buildEncodings(Node root) {
        List<Encoding> encodings = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node top = stack.pop();
            if (top.isLeaf()) {
                encodings.add(new Encoding(top.ch, top.encoding.toString()));
            } else {
                Node left = top.left;
                Node right = top.right;
                left.encoding.append(top.encoding);
                left.encoding.append('0');
                right.encoding.append(top.encoding);
                right.encoding.append('1');
                stack.push(left);
                stack.push(right);
            }
        }

        Collections.sort(encodings);
        return encodings;
    }

    private static class Encoding implements Comparable<Encoding> {
        char ch;
        String encoding;

        public Encoding(char ch, String encoding) {
            this.ch = ch;
            this.encoding = encoding;
        }

        @Override
        public String toString() {
            return ch + ": " + encoding;
        }

        @Override
        public int compareTo(Encoding o) {
            return ch - o.ch;
        }
    }

    private static class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left;
        Node right;
        StringBuilder encoding = new StringBuilder();

        public Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node o) {
            int d = freq - o.freq;
            if (d != 0) {
                return d;
            }
            return ch - o.ch;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (ch != node.ch) return false;
            return freq == node.freq;

        }

        @Override
        public int hashCode() {
            int result = (int) ch;
            result = 31 * result + freq;
            return result;
        }
    }
}
