import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Poker {

    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<PokerHand> pokerHands = PokerHand.parseHands(line);
                PokerHand left = pokerHands.get(0);
                PokerHand right = pokerHands.get(1);

                int comp = left.compareTo(right);
                if (comp < 0) {
                    System.out.println("right");
                } else if (comp > 0) {
                    System.out.println("left");
                } else {
                    System.out.println("none");
                }
            }
        }
    }

}

class PokerHand implements Comparable<PokerHand> {

    public static final int HIGH_CARD = 0;
    public static final int ONE_PAIR = 1;
    public static final int TWO_PAIRS = 2;
    public static final int THREE_OF_A_KIND = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int FOUR_OF_A_KIND = 7;
    public static final int STRAIGHT_FLUSH = 8;
    public static final int ROYAL_FLUSH = 9;

    public static final int MAX_RANKS = 13;
    public static final int MAX_SUITES = 4;
    public static final int MAX_CARDS_PER_HAND = 5;

    private final int value;
    private final List<Integer> highRanks;
    private final List<Integer> lowRanks;

    public PokerHand(int value, List<Integer> highRanks, List<Integer> lowRanks) {
        this.value = value;
        this.highRanks = highRanks;
        this.lowRanks = lowRanks;
    }

    public static List<PokerHand> parseHands(String s) {
        List<PokerHand> pokerHands = new ArrayList<>();
        String[] cards = s.split(" ");
        for (int i = 0; i < cards.length; i += MAX_CARDS_PER_HAND) {
            PokerHand hand = parseHandFromCards(Arrays.copyOfRange(cards, i, i + MAX_CARDS_PER_HAND));
            pokerHands.add(hand);
        }
        return pokerHands;
    }

    public static PokerHand parseHandFromCards(String[] cards) {
        int[] numInRank = new int[MAX_RANKS];
        int[] numInSuit = new int[MAX_SUITES];
        List<Integer> highRanks = new ArrayList<>();
        List<Integer> lowRanks = new ArrayList<>();

        for (String card : cards) {
            int rank = parseRank(card.charAt(0));
            int suit = parseSuit(card.charAt(1));
            numInRank[rank]++;
            numInSuit[suit]++;
            lowRanks.add(rank);
        }

        boolean flush = false;
        boolean straight = false;
        boolean four = false;
        boolean three = false;
        int pairs = 0;

        // check for flush
        for (int i = 0; i < MAX_SUITES; i++) {
            if (numInSuit[i] == MAX_CARDS_PER_HAND) {
                flush = true;
                break;
            }
        }

        // check for straight
        int rank = 0;
        int consecutive = 0;
        while (numInRank[rank] == 0) rank++;
        for (; rank < MAX_RANKS && numInRank[rank] > 0; rank++) {
            consecutive++;
        }
        if (consecutive == MAX_CARDS_PER_HAND) {
            straight = true;
        }

        // check for 4-of-a-kind, 3-of-a-kind and pairs
        for (rank = 0; rank < MAX_RANKS; rank++) {
            if (numInRank[rank] == 4) {
                four = true;
                highRanks.add(rank);
            }
            if (numInRank[rank] == 3) {
                three = true;
                highRanks.add(rank);
            }
            if (numInRank[rank] == 2) {
                pairs++;
                highRanks.add(rank);
            }
        }

        if (straight && flush) {
            highRanks.addAll(lowRanks);
            lowRanks.clear();
            if (highRanks.get(MAX_CARDS_PER_HAND - 1) == MAX_RANKS - 1) {
                return new PokerHand(ROYAL_FLUSH, highRanks, lowRanks);
            }
            return new PokerHand(STRAIGHT_FLUSH, highRanks, lowRanks);
        }

        if (four) {
            for (int highRank : highRanks) {
                lowRanks.removeAll(Collections.singleton(highRank));
            }
            return new PokerHand(FOUR_OF_A_KIND, highRanks, lowRanks);
        }

        if (three && pairs == 1) {
            for (int highRank : highRanks) {
                lowRanks.removeAll(Collections.singleton(highRank));
            }
            return new PokerHand(FULL_HOUSE, highRanks, lowRanks);
        }

        if (flush) {
            highRanks.addAll(lowRanks);
            lowRanks.clear();
            return new PokerHand(FLUSH, highRanks, lowRanks);
        }

        if (straight) {
            highRanks.addAll(lowRanks);
            lowRanks.clear();
            return new PokerHand(STRAIGHT, highRanks, lowRanks);
        }

        if (three) {
            for (int highRank : highRanks) {
                lowRanks.removeAll(Collections.singleton(highRank));
            }
            return new PokerHand(THREE_OF_A_KIND, highRanks, lowRanks);
        }

        if (pairs == 2) {
            for (int highRank : highRanks) {
                lowRanks.removeAll(Collections.singleton(highRank));
            }
            return new PokerHand(TWO_PAIRS, highRanks, lowRanks);
        }

        if (pairs == 1) {
            for (int highRank : highRanks) {
                lowRanks.removeAll(Collections.singleton(highRank));
            }
            return new PokerHand(ONE_PAIR, highRanks, lowRanks);
        }

        highRanks.addAll(lowRanks);
        lowRanks.clear();
        return new PokerHand(HIGH_CARD, highRanks, lowRanks);
    }

    private static int parseRank(char c) {
        int rank;
        switch (c) {
            case '2':
                rank = 0;
                break;
            case '3':
                rank = 1;
                break;
            case '4':
                rank = 2;
                break;
            case '5':
                rank = 3;
                break;
            case '6':
                rank = 4;
                break;
            case '7':
                rank = 5;
                break;
            case '8':
                rank = 6;
                break;
            case '9':
                rank = 7;
                break;
            case 'T':
                rank = 8;
                break;
            case 'J':
                rank = 9;
                break;
            case 'Q':
                rank = 10;
                break;
            case 'K':
                rank = 11;
                break;
            case 'A':
                rank = 12;
                break;
            default:
                throw new IllegalArgumentException("unknown rank '" + c + "'");
        }
        return rank;
    }

    private static int parseSuit(char c) {
        int suit;
        switch (c) {
            case 'C':
                suit = 0;
                break;
            case 'D':
                suit = 1;
                break;
            case 'H':
                suit = 2;
                break;
            case 'S':
                suit = 3;
                break;
            default:
                throw new IllegalArgumentException("unknown suit '" + c + "'");
        }
        return suit;
    }

    @Override
    public int compareTo(PokerHand o) {
        if (this.value > o.value) {
            return 1;
        }
        if (this.value < o.value) {
            return -1;
        }

        int highRankComp = compare(this.highRanks, o.highRanks);
        if (highRankComp != 0) {
            return highRankComp;
        }

        return compare(this.lowRanks, o.lowRanks);
    }

    private static int compare(List<Integer> a, List<Integer> b) {
        Collections.sort(a, Collections.reverseOrder());
        Collections.sort(b, Collections.reverseOrder());
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (a.get(i) > b.get(i)) {
                return 1;
            }
            if (a.get(i) < b.get(i)) {
                return -1;
            }
        }
        return 0;
    }

}
