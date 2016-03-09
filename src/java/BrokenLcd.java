import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/open_challenges/179/
 *
 * @author cata
 */
public class BrokenLcd {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String testCase;
      while ((testCase = reader.readLine()) != null) {
        String[] lcdAndNumber = testCase.split(";");
        if (canDisplay(lcdAndNumber[0], lcdAndNumber[1])) {
          System.out.println(1);
        } else {
          System.out.println(0);
        }
      }
    }
  }

  private static boolean canDisplay(String lcd, String number) {
    String[] lcdDigits = lcd.split(" ");
    int index = 0;
    while (index + number.length() - 1 <= lcdDigits.length) {
      if (canDisplay(lcdDigits, index, number)) return true;
      index++;
    }
    return false;
  }

  private static boolean canDisplay(String[] lcdDigits, int index, String number) {
    for (int i = 0; i < number.length(); i++) {
      char digit = number.charAt(i);
      if (digit == '.') {
        if (!canDisplayDot(lcdDigits[index - 1])) return false;
        index--;
      } else {
        if (!canDisplayDigit(lcdDigits[index], digit)) return false;
      }
      index++;
    }
    return true;
  }

  private static boolean canDisplayDigit(String lcdDigit, char numberDigit) {
    int[] segment = SEGMENTS[Character.digit(numberDigit, 10)];
    for (int i = 0; i < segment.length; i++) {
      int segmentState = Character.digit(lcdDigit.charAt(i), 10);
      if (segment[i] == 1 && segmentState != 1) return false;
    }
    return true;
  }

  private static boolean canDisplayDot(String lcdDigit) {
    return lcdDigit.charAt(7) == '1';
  }

  private static final int[][] SEGMENTS = {
      {1, 1, 1, 1, 1, 1, 0},  // 0
      {0, 1, 1, 0, 0, 0, 0},  // 1
      {1, 1, 0, 1, 1, 0, 1},  // 2
      {1, 1, 1, 1, 0, 0, 1},  // 3
      {0, 1, 1, 0, 0, 1, 0},  // 4
      {1, 0, 1, 1, 0, 1, 1},  // 5
      {1, 0, 1, 1, 1, 1, 1},  // 6
      {1, 1, 1, 0, 0, 0, 0},  // 7
      {1, 1, 1, 1, 1, 1, 1},  // 8
      {1, 1, 1, 1, 0, 1, 1},  // 9
  };

}
