import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Solution to https://www.codeeval.com/open_challenges/222/
 */
public class BlackCard {

  public static void main(String[] args) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] playersAndNumber = line.split(" \\| ");
        List<String> players = asList(playersAndNumber[0].split(" "));
        int number = Integer.valueOf(playersAndNumber[1]);
        System.out.println(blackSpot(players, number));
      }
    }
  }

  private static List<String> asList(String[] strings) {
    List<String> stringList = new LinkedList<>();
    for (String s: strings) {
      stringList.add(s);
    }
    return stringList;
  }

  private static String blackSpot(List<String> players, int number) {
    int i, c;
    while (players.size() > 1) {
      i = 0;
      c = 1;
      while (c < number) {
        i = (i + 1) % players.size();
        c++;
      }
      players.remove(i);
    }
    return players.get(0);
  }

}
