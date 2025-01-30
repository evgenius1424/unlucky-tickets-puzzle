package com.github.evgenius1424;

import java.util.*;

import static com.github.evgenius1424.UnluckyTickets.createUnluckyTickets;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int combinationNumbers = 3;
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

        var combinations = generateUniqueCombinations(numbers, combinationNumbers);
        var tickets = createUnluckyTickets(combinations);

        System.out.println("Elapsed: " + (System.currentTimeMillis() - start));
        System.out.println("Tickets: " + tickets.size());
    }

    private static List<TicketSide> generateUniqueCombinations(int[] numbers, int combinationNumbers) {
        List<TicketSide> ticketSides = new ArrayList<>();
        generateCombinationsHelper(numbers, combinationNumbers, 0, new ArrayList<>(), ticketSides);
        return ticketSides;
    }

    private static void generateCombinationsHelper(int[] numbers, int combinationNumbers, int start, List<Integer> current, List<TicketSide> ticketSides) {
        if (current.size() == combinationNumbers) {
            ticketSides.add(new TicketSide(new ArrayList<>(current)));
            return;
        }

        for (int i = start; i < numbers.length; i++) {
            current.add(numbers[i]);
            generateCombinationsHelper(numbers, combinationNumbers, i, current, ticketSides);
            current.remove(current.size() - 1);
        }
    }

}