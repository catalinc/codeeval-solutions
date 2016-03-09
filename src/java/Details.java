import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Details {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(movesDetail(line));
      }
    }
  }

  private static int movesDetail(String matrix) {
    int minMoves = Integer.MAX_VALUE;
    String[] lines = matrix.split(",");
    for (String line : lines) {
      int lastX = -1;
      for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (c == 'X') lastX = i;
        if (c == 'Y') {
          int moves = i - lastX - 1;
          if (moves < minMoves) minMoves = moves;
          break;
        }
      }
      if (minMoves == 0) break;
    }
    return minMoves;
  }
}
