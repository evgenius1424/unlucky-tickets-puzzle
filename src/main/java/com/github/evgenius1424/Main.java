package com.github.evgenius1424;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.disjoint;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        var tickets = createUnluckyTickets();
        System.out.println("Elapsed: " + (System.currentTimeMillis() - start));
        System.out.println("Tickets: " + tickets.size());
//        System.out.println(tickets.stream().sorted().toList());
    }

    private static Collection<String> createUnluckyTickets() {
        int combinationNumbers = 3;
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

        int[][] permutationsWithRepetitions = getPermutationsWithRepetitions(numbers, combinationNumbers);

        List<Combination> combinations = Arrays.stream(permutationsWithRepetitions)
                .map(p -> new Combination(Arrays.stream(p).boxed().collect(Collectors.toList())))
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

    public static int[][] getPermutationsWithRepetitions(int[] source, int variationLength) {
        int srcLength = source.length;
        int permutations = (int) Math.pow(srcLength, variationLength);

        int[][] table = new int[permutations][variationLength];

        for (int i = 0; i < variationLength; i++) {
            int t2 = (int) Math.pow(srcLength, i);
            for (int p1 = 0; p1 < permutations; ) {
                for (int a : source) {
                    for (int p2 = 0; p2 < t2; p2++) {
                        table[p1][i] = a;
                        p1++;
                    }
                }
            }
        }
        return table;
    }

}

