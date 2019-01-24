import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        List<Combination> combinations = createCombinations();
        Collection<String> tickets = createUnluckyTicketsFromCombinations(combinations);

        System.out.println("Time: " + (System.currentTimeMillis() - time));
        System.out.println("Tickets: " + tickets.size());
    }

    private static List<Combination> createCombinations() {
        List<Combination> combinations = new ArrayList<>(1000);

        PermutationsWithRepetition permutationsUtil = new PermutationsWithRepetition(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 3);
        int[][] permutations = permutationsUtil.getPermutations();
        for (int[] x : permutations) {
            combinations.add(new Combination(x[0], x[1], x[2]));
        }

        return combinations;
    }

    private static Collection<String> createUnluckyTicketsFromCombinations(List<Combination> combinations) {
        Set<String> tickets = new HashSet<>();

        for (int i = 0; i < combinations.size(); i++) {
            for (int k = i + 1; k < combinations.size(); k++) {
                Combination comb1 = combinations.get(i);
                Combination comb2 = combinations.get(k);
                if (!Collections.disjoint(comb1.getValues(), comb2.getValues())) {
                    continue;
                }
                tickets.add(comb1.toString() + comb2.toString());
                tickets.add(comb2.toString() + comb1.toString());
            }
        }

        return tickets;
    }
}

