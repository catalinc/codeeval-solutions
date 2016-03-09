import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/browse/174/
 */
public class SlangFlavor {

    private static final String[] SLANG_PHRASES = {
            ", yeah!",
            ", this is crazy, I tell ya.",
            ", can U believe this?",
            ", eh?",
            ", aw yea.",
            ", yo.",
            "? No way!",
            ". Awesome!"
    };

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            int punctuationMarkCount = 0;
            int slangPhraseIndex = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder output = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    switch (ch) {
                        case '.':
                        case '?':
                        case '!':
                            punctuationMarkCount++;
                            if (punctuationMarkCount % 2 == 0) {
                                output.append(SLANG_PHRASES[slangPhraseIndex]);
                                slangPhraseIndex = (slangPhraseIndex + 1) % SLANG_PHRASES.length;
                            } else {
                                output.append(ch);
                            }
                            break;
                        default:
                            output.append(ch);
                    }
                }
                System.out.println(output);
            }
        }
    }

}
