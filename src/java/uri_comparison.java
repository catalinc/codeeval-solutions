import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class uri_comparison {

    public static boolean URIEqual(String s1, String s2) throws URISyntaxException {
        URI uri1 = parseURI(s1);
        URI uri2 = parseURI(s2);
        return uri1.equals(uri2);
    }

    private static URI parseURI(String s) throws URISyntaxException {
        URI uri = new URI(s);
        return new URI(uri.getScheme(),
                uri.getUserInfo(),
                uri.getHost(),
                uri.getPort() == -1 ? 80 : uri.getPort(),
                uri.getPath(),
                uri.getQuery(),
                uri.getFragment());
    }

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split(";");
                if (URIEqual(strings[0], strings[1])) {
                    out.println("True");
                } else {
                    out.println("False");
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

}
