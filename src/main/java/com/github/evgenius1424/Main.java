package com.github.evgenius1424;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.disjoint;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        var tickets = createUnluckyTickets();
        System.out.println("Elapsed: " + (System.currentTimeMillis() - start));
        System.out.println("Tickets: " + tickets.size());
    }

    private static Collection<String> createUnluckyTickets() {
        int combinationNumbers = 3;
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

        List<Combination> combinations = generateUniqueCombinations(numbers, combinationNumbers);

        Collection<String> tickets = new HashSet<>();
        for (int i = 0; i < combinations.size(); i++) {
            for (int k = i + 1; k < combinations.size(); k++) {
                var firstCombination = combinations.get(i);
                var secondCombination = combinations.get(k);
                if (disjoint(firstCombination.getValues(), secondCombination.getValues())) {
                    List<String> firstPermutations = generatePermutations(firstCombination);
                    List<String> secondPermutations = generatePermutations(secondCombination);

                    for (String firstPermutation : firstPermutations) {
                        for (String secondPermutation : secondPermutations) {
                            tickets.add(firstPermutation + secondPermutation);
                            tickets.add(secondPermutation + firstPermutation);
                        }
                    }
                }
            }
        }

        return tickets;
    }

    private static List<Combination> generateUniqueCombinations(int[] numbers, int combinationNumbers) {
        List<Combination> combinations = new ArrayList<>();
        generateCombinationsHelper(numbers, combinationNumbers, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private static void generateCombinationsHelper(int[] numbers, int combinationNumbers, int start, List<Integer> current, List<Combination> combinations) {
        if (current.size() == combinationNumbers) {
            combinations.add(new Combination(new ArrayList<>(current)));
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            current.add(numbers[i]);
            generateCombinationsHelper(numbers, combinationNumbers, i, current, combinations);
            current.remove(current.size() - 1);
        }
    }

    private static List<String> generatePermutations(Combination combination) {
        List<String> permutations = new ArrayList<>();
        permute(combination.getNumbers(), 0, permutations);
        return permutations;
    }

    private static void permute(List<Integer> numbers, int start, List<String> permutations) {
        if (start == numbers.size() - 1) {
            permutations.add(numbers.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }

        for (int i = start; i < numbers.size(); i++) {
            Collections.swap(numbers, start, i); // Swap to create a new permutation
            permute(numbers, start + 1, permutations);
            Collections.swap(numbers, start, i); // Backtrack
        }
    }
}