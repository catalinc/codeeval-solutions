import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnightMoves {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Pos p = parsePos(line);
        System.out.println(format(moves(p)));
      }
    }
  }

  private static class Pos {
    private int row;
    private int col;

    private Pos(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  private static Pos parsePos(String s) {
    int r = s.charAt(1) - '0' - 1;
    int c = s.charAt(0) - 'a';
    return new Pos(r, c);
  }

  private static final int[] OFFSETS = new int[]{-2, -1, 1, 2};

  private static List<Pos> moves(Pos p) {
    List<Pos> moves = new ArrayList<>();
    for (int r : OFFSETS) {
      for (int c : OFFSETS) {
        if (Math.abs(r) != Math.abs(c)) {
          int newRow = p.row + r;
          int newCol = p.col + c;
          if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7) continue;
          moves.add(new Pos(newRow, newCol));
        }
      }
    }
    return moves;
  }

  private static String format(List<Pos> ps) {
    List<String> ss = new ArrayList<>();
    for (int i = 0; i < ps.size(); i++) {
      ss.add(format(ps.get(i)));
    }
    Collections.sort(ss);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < ss.size(); i++) {
      sb.append(ss.get(i));
      if (i != ss.size() - 1) sb.append(' ');
    }
    return sb.toString();
  }

  private static String format(Pos pos) {
    return String.format("%c%d", (char) (pos.col + 'a'), pos.row + 1);
  }

}
