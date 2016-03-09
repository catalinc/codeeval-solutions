import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Solution to https://www.codeeval.com/open_challenges/230/
 */
public class Football {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                SortedMap<Integer, SortedSet<Integer>> teamsAndCountries = parseTeamsAndCountries(line);
                printTeams(teamsAndCountries);
            }
        }
    }

    private static void printTeams(SortedMap<Integer, SortedSet<Integer>> teamsAndCountries) {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Integer> itTeams = teamsAndCountries.keySet().iterator(); itTeams.hasNext(); ) {
            Integer teamId = itTeams.next();
            sb.append(teamId).append(':');
            for (Iterator<Integer> itCountries = teamsAndCountries.get(teamId).iterator(); itCountries.hasNext(); ) {
                Integer countryId = itCountries.next();
                sb.append(countryId);
                if (itCountries.hasNext()) {
                    sb.append(',');
                }
            }
            sb.append("; ");
        }
        System.out.println(sb.toString());
    }

    private static SortedMap<Integer, SortedSet<Integer>> parseTeamsAndCountries(String s) {
        SortedMap<Integer, SortedSet<Integer>> teamsAndCountries = new TreeMap<>();
        String[] countries = s.split(" \\| ");
        for (int i = 0; i < countries.length; i++) {
            int countryId = i + 1;
            int[] teams = parseIntArray(countries[i]);
            for (int j = 0; j < teams.length; j++) {
                int teamId = teams[j];
                SortedSet<Integer> countriesForTeam = teamsAndCountries.get(teamId);
                if (countriesForTeam == null) {
                    countriesForTeam = new TreeSet<>();
                    teamsAndCountries.put(teamId, countriesForTeam);
                }
                countriesForTeam.add(countryId);
            }
        }
        return teamsAndCountries;
    }

    private static int[] parseIntArray(String s) {
        String[] tokens = s.split(" ");
        int[] array = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            array[i] = Integer.parseInt(tokens[i]);
        }
        return array;
    }
}
