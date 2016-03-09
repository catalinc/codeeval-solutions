import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class levenshtein_distance {

    private static Trie loadWords(final String fileName) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            final Trie trie = new Trie();
            String word;
            while ((word = in.readLine()) != null) {
                trie.insert(word);
            }
            return trie;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    private static int countFriends(final String word, final Trie trie) {
        final HashSet<String> processed = new HashSet<String>();
        final TreeSet<String> toProcess = trie.search(word, 1);
        while (!toProcess.isEmpty()) {
            final String friend = toProcess.first();
            processed.add(friend);
            toProcess.remove(friend);
            for (final String friendFriend : trie.search(friend, 1)) {
                if (!processed.contains(friendFriend) && !toProcess.contains(friendFriend)) {
                    toProcess.add(friendFriend);
                }
            }
        }
        return processed.size();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: java " + levenshtein_distance.class.getName() + " filename");
            System.exit(1);
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(System.out);
            final Trie trie = loadWords(args[0]);
            out.println(countFriends("hello", trie));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}

class Node {
    final char letter;
    final HashMap<Character, Node> children;
    String word;

    Node(final char letter) {
        this.letter = letter;
        this.children = new HashMap<Character, Node>();
    }

    boolean isWord() {
        return word != null;
    }
}

class Trie {
    final Node root;

    Trie() {
        root = new Node(' ');
    }

    void insert(final String word) {
        Node n = root;
        for (int i = 0; i < word.length(); i++) {
            final char letter = word.charAt(i);
            if (!n.children.containsKey(letter)) {
                n.children.put(letter, new Node(letter));
            }
            n = n.children.get(letter);
        }
        n.word = word;
    }

    /**
     * Returns a set  all words that are at given edit distance from target word.
     */
    TreeSet<String> search(final String word, final int editDistance) {
        final TreeSet<String> results = new TreeSet<String>();

        final int[] currentRow = new int[word.length() + 1];
        for (int i = 0; i <= word.length(); i++) {
            currentRow[i] = i;
        }

        for (final Node node : root.children.values()) {
            searchRecursive(word, editDistance, node, currentRow, results);
        }
        return results;
    }

    private void searchRecursive(final String word,
                                 final int editDistance,
                                 final Node node,
                                 final int[] previousRow,
                                 final Collection<String> results) {
        final int columns = word.length() + 1;
        final int[] currentRow = new int[columns];
        currentRow[0] = previousRow[0] + 1;

        for (int i = 1; i < columns; i++) {
            final int insertCost = currentRow[i - 1] + 1;
            final int deleteCost = previousRow[i] + 1;
            int replaceCost = previousRow[i - 1];
            if (word.charAt(i - 1) != node.letter) {
                replaceCost++;
            }
            currentRow[i] = min(insertCost, deleteCost, replaceCost);
        }

        if (currentRow[columns - 1] == editDistance && node.isWord()) {
            results.add(node.word);
        }

        if (min(currentRow) <= editDistance) {
            for (final Node child : node.children.values()) {
                searchRecursive(word, editDistance, child, currentRow, results);
            }
        }
    }

    private int min(int... values) {
        int min = Integer.MAX_VALUE;
        for (int i : values) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
}