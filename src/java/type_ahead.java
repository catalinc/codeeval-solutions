import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class type_ahead {

    private static final String TEXT =
            "Mary had a little lamb its fleece was white as snow;\n" +
            "And everywhere that Mary went, the lamb was sure to go. \n" +
            "It followed her to school one day, which was against the rule;\n" +
            "It made the children laugh and play, to see a lamb at school.\n" +
            "And so the teacher turned it out, but still it lingered near,\n" +
            "And waited patiently about till Mary did appear.\n" +
            "\"Why does the lamb love Mary so?\" the eager children cry;" +
            "\"Why, Mary loves the lamb, you know\" the teacher did reply.\"";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            NGram ngram = new NGram();
            ngram.addText(TEXT);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                int n = Integer.valueOf(tokens[0]);
                String word = tokens[1];
                List<Prediction> predictions = ngram.predict(n, word);
                out.println(NGram.join(predictions, ';'));
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

class NGram {
    private List<String> words;

    public NGram() {
        words = new ArrayList<String>();
    }

    public void addText(String text) {
        Matcher matcher = Pattern.compile("\\w+").matcher(text);
        while (matcher.find()) {
            String word = matcher.group();
            words.add(word);
        }
    }

    public List<Prediction> predict(int n, String word) {
        List<String> ngrams = ngrams(n);
        int totalOccurrences = 0;
        WordCounter wordCounter = new WordCounter();
        for (String ngram : ngrams) {
            if (ngram.startsWith(word)) {
                String lastWord = lastWord(ngram);
                wordCounter.increment(lastWord);
                totalOccurrences++;
            }
        }

        List<Prediction> predictions = new ArrayList<Prediction>(wordCounter.size());
        for (String predictedWord : wordCounter.words()) {
            int occurrences = wordCounter.occurrences(predictedWord);
            predictions.add(new Prediction(predictedWord, (double) occurrences / totalOccurrences));
        }
        Collections.sort(predictions, new Comparator<Prediction>() {
            public int compare(Prediction o1, Prediction o2) {
                if (o1.score < o2.score) {
                    return 1;
                }
                if (o1.score > o2.score) {
                    return -1;
                }
                return o1.word.compareTo(o2.word);
            }
        });
        return predictions;
    }

    public List<String> ngrams(int n) {
        List<String> ngrams = new ArrayList<String>();
        for (int i = 0; i < words.size() - n; i++) {
            ngrams.add(join(words.subList(i, i + n), ' '));
        }
        return ngrams;
    }

    private String lastWord(String s) {
        int lastSpacePos = s.lastIndexOf(' ');
        return s.substring(lastSpacePos + 1);
    }

    static String join(List<?> items, char sep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
            if (i != items.size() - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

}

class Prediction {
    final String word;
    final double score;

    Prediction(String word, double score) {
        this.word = word;
        this.score = score;
    }

    public String toString() {
        return String.format("%s,%.3f", word, score);
    }
}

class WordCounter {

    private Map<String, MutableInt> occurrencesMap;

    public WordCounter() {
        occurrencesMap = new HashMap<String, MutableInt>();
    }

    public void increment(String word) {
        MutableInt mutableInt = occurrencesMap.get(word);
        if (mutableInt == null) {
            mutableInt = new MutableInt();
            occurrencesMap.put(word, mutableInt);
        }
        mutableInt.value++;
    }

    public int occurrences(String word) {
        MutableInt mutableInt = occurrencesMap.get(word);
        if (mutableInt == null) {
            return 0;
        }
        return mutableInt.value;
    }

    public int size() {
        return occurrencesMap.size();
    }

    public Set<String> words() {
        return occurrencesMap.keySet();
    }

    static class MutableInt {
        int value;
    }

}

