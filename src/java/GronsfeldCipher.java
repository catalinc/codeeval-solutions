import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/open_challenges/181/
 *
 * @author cata
 */
public class GronsfeldCipher {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String testCase;
      while ((testCase = reader.readLine()) != null) {
        String[] keyAndMessage = testCase.split(";");
        System.out.println(decode(keyAndMessage[0], keyAndMessage[1]));
      }
    }
  }

  private static String decode(String key, String message) {
    StringBuilder decoded = new StringBuilder();
    int i = 0, j = 0;
    while (i < message.length()) {
      int o = Character.digit(key.charAt(j), 10);
      char c = message.charAt(i);
      decoded.append(shift(c, o));
      j = (j + 1) % key.length();
      i++;
    }
    return decoded.toString();
  }

  private static char shift(char c, int o) {
    return ALPHABET[mod(indexOf(c) - o, ALPHABET.length)];
  }

  private static int mod(int a, int b) {
    int r = a % b;
    if (r < 0) {
      r += b;
    }
    return r;
  }

  private static int indexOf(char c) {
    for (int i = 0; i < ALPHABET.length; i++) {
      if (ALPHABET[i] == c) return i;
    }
    throw new IllegalArgumentException(c + " not found in the alphabet");
  }

  private static final char[] ALPHABET = " !\"#$%&'()*+,-./0123456789:<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
}
