package com.github.evgenius1424;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static com.github.evgenius1424.Permutations.getPermutationsWithRepetitions;
import static java.util.Collections.disjoint;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        var tickets = createUnluckyTickets();
        System.out.println("Elapsed: " + (System.currentTimeMillis() - start));
        System.out.println("Tickets: " + tickets.size());
        System.out.println(tickets.stream().sorted().toList());
    }

    private static Collection<String> createUnluckyTickets() {
        var combinations = Arrays.stream(getPermutationsWithRepetitions(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 3))
                .map(p -> new Combination(p[0], p[1], p[2]))
                .toList();

        Collection<String> tickets = new HashSet<>();
        for (int i = 0; i < combinations.size(); i++) {
            for (int k = i + 1; k < combinations.size(); k++) {
                var firstCombination = combinations.get(i);
                var secondCombination = combinations.get(k);
                if (disjoint(firstCombination.getValues(), secondCombination.getValues())) {
                    tickets.add(firstCombination + secondCombination.toString());
                    tickets.add(secondCombination + firstCombination.toString());
                }
            }
        }

        return tickets;
    }

}

