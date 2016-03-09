import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Solution to https://www.codeeval.com/open_challenges/212/
 */
public class RoboAndRobitta {

  static int walk(int w, int h, int x, int y) {
    int steps = 0, t;
    while (h > y) {
      steps += w;
      t = w;
      w = h - 1;
      h = t;
      t = x;
      x = w + 1 - y;
      y = t;
    }
    return steps + x;
  }

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] gridAndRobitta = line.split(" \\| ");

        String[] gridBounds = gridAndRobitta[0].split("x");
        int width = Integer.valueOf(gridBounds[0]);
        int height = Integer.valueOf(gridBounds[1]);

        String[] robittaPos = gridAndRobitta[1].split(" ");
        int x = Integer.valueOf(robittaPos[0]);
        int y = Integer.valueOf(robittaPos[1]);

        System.out.println(walk(width, height, x, y));
      }
    }

  }
}
