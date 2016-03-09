import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Solution to https://www.codeeval.com/open_challenges/223/
 */
public class AlternativeReality {

  private static final int[] coins = {1, 5, 10, 25, 50};

  private static final int[] ways = new int[101];

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String line;
      while ((line = reader.readLine()) != null) {
        int n = Integer.valueOf(line);
        System.out.println(countWays(n));
      }
    }
  }

  private static int countWays(int n) {
    Arrays.fill(ways, 0);
    ways[0] = 1;
    for (int i = 0; i < coins.length; i++) {
      for (int j = coins[i]; j <= n; j++) {
        ways[j] += ways[j - coins[i]];
      }
    }
    return ways[n];
  }
}
