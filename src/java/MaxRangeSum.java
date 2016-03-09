import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/open_challenges/186/
 *
 * @author cata
 */
public class MaxRangeSum {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String testCase;
      while ((testCase = reader.readLine()) != null) {
        int gain = maxGain(testCase);
        if (gain > 0) {
          System.out.println(gain);
        } else {
          System.out.println(0);
        }
      }
    }
  }

  private static int maxGain(String testCase) {
    String[] numDaysAndMarketData = testCase.split(";");
    int numDays = Integer.valueOf(numDaysAndMarketData[0]);
    int[] marketData = parseIntArray(numDaysAndMarketData[1]);
    int maxGain = Integer.MIN_VALUE;
    for (int i = 0; i + numDays <= marketData.length; i++) {
      int gain = 0;
      for (int j = i; j < i + numDays; j++) {
        gain += marketData[j];
      }
      if (gain > maxGain) {
        maxGain = gain;
      }
    }
    return maxGain;
  }

  private static int[] parseIntArray(String s) {
    String[] tokens = s.split(" ");
    int[] ints = new int[tokens.length];
    for (int i = 0; i < tokens.length; i++) {
      ints[i] = Integer.valueOf(tokens[i]);
    }
    return ints;
  }

}
