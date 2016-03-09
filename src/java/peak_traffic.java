import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class peak_traffic {

    private static LinkedHashMap<String, HashSet<String>> interactions;

    private static void solve(String fileName, PrintWriter out) throws FileNotFoundException {
        parseLog(fileName);
        ArrayList<HashSet<String>> clustersSoFar =
                new ArrayList<HashSet<String>>(interactions.size());
        for (String user : interactions.keySet()) {
            HashSet<String> bestCluster = new HashSet<String>();
            for (HashSet<String> cluster : clustersSoFar) {
                if (accept(cluster, user) && cluster.size() > bestCluster.size()) {
                    bestCluster = cluster;
                }
            }
            bestCluster.add(user);
            if (bestCluster.size() == 1) {
                clustersSoFar.add(bestCluster);
            }
        }
        ArrayList<String> clustersToPrint = new ArrayList<String>(clustersSoFar.size());
        for (HashSet<String> cluster : clustersSoFar) {
            if (cluster.size() >= 3) {
                clustersToPrint.add(clusterToString(cluster));
            }
        }
        Collections.sort(clustersToPrint);
        for (String s : clustersToPrint) {
            out.println(s);
        }
    }

    private static LinkedHashMap<String, HashSet<String>> parseLog(String fileName)
            throws FileNotFoundException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            interactions = new LinkedHashMap<String, HashSet<String>>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() == 0) {
                    continue;
                }
                String[] tokens = line.split("\t");
                String userA = tokens[1];
                String userB = tokens[2];
                HashSet<String> users = interactions.get(userA);
                if (users == null) {
                    users = new HashSet<String>();
                    interactions.put(userA, users);
                }
                users.add(userB);
            }
            return interactions;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static boolean accept(HashSet<String> cluster, String user) {
        if (cluster.contains(user)) {
            return false;
        }
        for (String userInCluster : cluster) {
            HashSet<String> userInClusterFriends = interactions.get(userInCluster);
            HashSet<String> userFriends = interactions.get(user);
            if (userFriends == null) {
                return false;
            }
            if (!(userFriends.contains(userInCluster) && userInClusterFriends.contains(user))) {
                return false;
            }
        }
        return true;
    }

    private static String clusterToString(HashSet<String> cluster) {
        String[] users = cluster.toArray(new String[cluster.size()]);
        Arrays.sort(users);
        return join(users, ", ");
    }

    private static String join(String[] strings, String sep) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if (i != strings.length - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(System.out);
        try {
            solve(args[0], out);
        } finally {
            out.close();
        }
    }

}
