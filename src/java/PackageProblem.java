import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author cata
 */
public class PackageProblem {

    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.length() > 0) {
                        Input input = parseInput(line);
                        Solution solutionDynamicProgramming = knapsackDynamicProgramming(input);
                        Solution solutionGreedy = knapsackGreedy(input);
                        if (solutionDynamicProgramming.totalCost > solutionGreedy.totalCost) {
                            printSolution(solutionDynamicProgramming);
                        } else if (solutionDynamicProgramming.totalCost < solutionGreedy.totalCost) {
                            printSolution(solutionGreedy);
                        } else {
                            if (solutionDynamicProgramming.totalWeight < solutionGreedy.totalWeight) {
                                printSolution(solutionDynamicProgramming);
                            } else {
                                printSolution(solutionGreedy);
                            }
                        }
                    }
                }
            }
        }
    }

    private static Input parseInput(String line) {
        Input input = new Input();
        String[] capacityAndItems = line.split(" : ");
        String[] items = capacityAndItems[1].split(" ");

        input.capacity = Integer.parseInt(capacityAndItems[0]) * 100;
        input.items = new Item[items.length];

        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            String[] itemData = item.substring(1, item.length() - 1).split(",");
            int weight = (int) (Double.parseDouble(itemData[1]) * 100);
            int cost = Integer.parseInt(itemData[2].substring(1));
            input.items[i] = new Item(i+1, weight, cost);
        }

        return input;
    }

    private static Solution knapsackDynamicProgramming(Input input) {
        int[][] matrix = new int[input.items.length + 1][input.capacity + 1];
        for (int i = 1; i <= input.items.length; i++) {
            for (int j = 0; j <= input.capacity; j++) {
                if (input.items[i - 1].weight <= j) {
                    matrix[i][j] = Math.max(matrix[i - 1][j], input.items[i-1].cost + matrix[i - 1][j - input.items[i-1].weight]);
                } else {
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }
        List<Item> sol = new ArrayList<>();
        int i = input.items.length;
        for (int j = input.capacity; j >= 0 && i > 0; i--) {
            if (matrix[i][j] != matrix[i - 1][j]) {
                sol.add(input.items[i-1]);
                j -= input.items[i-1].weight;
            }
        }
        return new Solution(sol.toArray(new Item[sol.size()]));
    }

    private static Solution knapsackGreedy(Input input) {
        Arrays.sort(input.items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.costPerUnit > o2.costPerUnit) return -1;
                return 1;
            }
        });

        ArrayList<Item> sol = new ArrayList<>();
        for(int i = 0; i < input.items.length && input.capacity > 0; i++) {
            Item item = input.items[i];
            if (item.weight <= input.capacity) {
                sol.add(item);
                input.capacity -= item.weight;
            }
        }

        return new Solution(sol.toArray(new Item[sol.size()]));
    }

    private static void printSolution(Solution solution) {
        StringBuilder builder = new StringBuilder();
        if (solution.items.length == 0) {
            builder.append("-");
        } else {
            Arrays.sort(solution.items, new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return o1.index - o2.index;
                }
            });
            builder.append(solution.items[0].index);
            for (int i = 1; i < solution.items.length; i++) {
                builder.append(',');
                builder.append(solution.items[i].index);
            }
        }
        System.out.println(builder);
    }

}

class Input {
    int capacity;
    Item[] items;
}

class Item {
    int index;
    int weight;
    int cost;
    float costPerUnit;

    Item(int index, int weight, int cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
        this.costPerUnit = (float) this.cost / weight;
    }
}

class Solution {
    Item[] items;
    int totalCost;
    int totalWeight;

    public Solution(Item[] items) {
        this.items = items;
        for(Item item: items) {
            this.totalCost += item.cost;
            this.totalWeight += item.weight;
        }
    }
}