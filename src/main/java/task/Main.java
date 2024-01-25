package task;

import java.util.*;

import static java.util.Collections.disjoint;
import static task.Util.getPermutationsWithRepetitions;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        var tickets = createUnluckyTickets();
        System.out.println("Elapsed: " + (System.currentTimeMillis() - start));
        System.out.println("Tickets: " + tickets.size());
    }

    private static Collection<String> createUnluckyTickets() {
        var combinations = createCombinations();

        Set<String> tickets = new HashSet<>();
        for (int i = 0; i < combinations.size(); i++) {
            for (int k = i + 1; k < combinations.size(); k++) {
                var comb1 = combinations.get(i);
                var comb2 = combinations.get(k);
                if (!disjoint(comb1.getValues(), comb2.getValues())) {
                    continue;
                }
                tickets.add(comb1 + comb2.toString());
                tickets.add(comb2 + comb1.toString());
            }
        }

        return tickets;
    }

    private static List<Combination> createCombinations() {
        return Arrays.stream(getPermutationsWithRepetitions(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 3))
                .map(p -> new Combination(p[0], p[1], p[2])).toList();
    }
}

