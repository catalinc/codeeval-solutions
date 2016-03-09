import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class cash_register {

    public static LinkedList<Currency> computeChange(final int purchasePrice, final int cash) {
        final LinkedList<Currency> change = new LinkedList<Currency>();
        int diff = cash - purchasePrice;

        if (diff < 0) {
            change.add(Currency.ERROR);
            return change;
        }

        if (diff == 0) {
            change.add(Currency.ZERO);
            return change;
        }

        final Currency[] currencies = Currency.values();
        for (int i = 0; i < currencies.length && diff > 0; i++) {
            final Currency c = currencies[i];
            if (diff >= c.cents()) {
                change.add(c);
                diff -= c.cents();
                i--;
            }
        }

        return change;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new FileReader(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                final String line = scanner.nextLine();
                final String[] tokens = line.split(";");
                final int purchasePrice = parseCents(tokens[0]);
                final int cash = parseCents(tokens[1]);
                final LinkedList<Currency> change = computeChange(purchasePrice, cash);
                if (change.size() > 1) {
                    sortByDisplayName(change);
                }
                out.println(join(change, ','));
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

    private static int parseCents(final String s) {
        Float f = Float.valueOf(s);
        f = f * 100;
        return f.intValue();
    }

    private static void sortByDisplayName(final LinkedList<Currency> change) {
        Collections.sort(change, new Comparator<Currency>() {
            public int compare(Currency o1, Currency o2) {
                return o1.displayName().compareTo(o2.displayName());
            }
        });
    }

    private static String join(final LinkedList<Currency> change, final char sep) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < change.size(); i++) {
            sb.append(change.get(i).displayName());
            if (i != change.size() - 1) {
                sb.append(sep);
            }
        }
        return sb.toString();
    }
}

enum Currency {

    ONE_HUNDRED("ONE HUNDRED", 10000),
    FIFTY("FIFTY", 5000),
    TWENTY("TWENTY", 2000),
    TEN("TEN", 1000),
    FIVE("FIVE", 500),
    TWO("TWO", 200),
    ONE("ONE", 100),
    HALF_DOLLAR("HALF DOLLAR", 50),
    QUARTER("QUARTER", 25),
    DIME("DIME", 10),
    NICKEL("NICKEL", 5),
    PENNY("PENNY", 1),
    ZERO("ZERO", 0),
    ERROR("ERROR", -1);

    private String displayName;
    private int cents;

    private Currency(String displayName, int cents) {
        this.displayName = displayName;
        this.cents = cents;
    }

    public String displayName() {
        return displayName;
    }

    public int cents() {
        return cents;
    }
}
