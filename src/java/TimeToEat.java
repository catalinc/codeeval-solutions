import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Solution to https://www.codeeval.com/open_challenges/214/
 */
public class TimeToEat {

  static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

  public static void main(String[] args) throws IOException, ParseException {
    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      String testCase;
      while ((testCase = reader.readLine()) != null) {
        List<Date> dates = parseDates(testCase);
        Collections.sort(dates, new Comparator<Date>() {
          @Override
          public int compare(Date o1, Date o2) {
            return o2.compareTo(o1);
          }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dates.size(); i++) {
          sb.append(DATE_FORMAT.format(dates.get(i)));
          if (i < dates.size() - 1) {
            sb.append(' ');
          }
        }
        System.out.println(sb);
      }
    }
  }

  static List<Date> parseDates(String s) throws ParseException {
    List<Date> dates = new ArrayList<>();
    for (String t: s.split(" ")) {
      Date d = DATE_FORMAT.parse(t);
      dates.add(d);
    }
    return dates;
  }

}
