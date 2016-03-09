import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Solution to https://www.codeeval.com/open_challenges/220/
 */
public class TrickOrTreat {

  private static int candiesPerChild(String s) {
    List<Integer> candiesAndHouses = parseInts(s);
    int total = 0;
    int count = 0;
    int houses = candiesAndHouses.get(3);
    for (int i = 3; i <= 5; i++) {
      int children = candiesAndHouses.get(i - 3);
      total += i * children * houses;
      count += children;
    }
    if (total == 0) return 0;
    return total / count;
  }

  private static List<Integer> parseInts(String s) {
    List<Integer> ints = new LinkedList<>();
    int v = 0;
    boolean found = false;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (Character.isDigit(ch)) {
        v = v * 10 + (ch - '0');
        found = true;
      } else {
        if (found) {
          ints.add(v);
          v = 0;
          found = false;
        }
      }
    }
    if (found) {
      ints.add(v);
    }
    return ints;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(candiesPerChild(line));
      }
    }
  }
}
