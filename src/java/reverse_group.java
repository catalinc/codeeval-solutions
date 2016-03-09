import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class reverse_group {

    public static List<Integer> reverseGroups(List<Integer> integers, int k) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < integers.size(); i += k) {
            List<Integer> group = subList(integers, i, i + k);
            if (group.size() == k) {
                Collections.reverse(group);
            }
            result.addAll(group);
        }
        return result;
    }

    private static <E> List<E> subList(List<E> list, int fromIndex, int toIndex) {
        if (toIndex > list.size()) {
            toIndex = list.size();
        }
        List<E> subList = list.subList(fromIndex, toIndex);
        return new ArrayList<E>(subList);
    }

    private static List<Integer> parseIntList(String s) {
        List<Integer> integers = new ArrayList<Integer>();
        String[] strings = s.split(",");
        for (String string : strings) {
            integers.add(Integer.parseInt(string));
        }
        return integers;
    }

    private static String join(List<?> list, char sep) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            result.append(list.get(i));
            result.append(sep);
        }
        result.append(list.get(list.size() - 1));
        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = null;
        PrintWriter out = null;
        try {
            scanner = new Scanner(new File(args[0]));
            out = new PrintWriter(System.out);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] strings = line.split(";");
                List<Integer> integers = parseIntList(strings[0]);
                int k = Integer.parseInt(strings[1]);
                List<Integer> reversed = reverseGroups(integers, k);
                out.println(join(reversed, ','));
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
