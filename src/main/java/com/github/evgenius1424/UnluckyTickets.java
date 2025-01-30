package com.github.evgenius1424;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.evgenius1424.PermutationUtils.getPermutationsNoRepetitions;
import static java.util.Collections.disjoint;

public class UnluckyTickets {

    public static Collection<String> createUnluckyTickets(List<TicketSide> ticketSideList) {
        Collection<String> tickets = new HashSet<>();
        for (int i = 0; i < ticketSideList.size(); i++) {
            for (int k = i + 1; k < ticketSideList.size(); k++) {
                var firstCombination = ticketSideList.get(i);
                var secondCombination = ticketSideList.get(k);
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

    private static List<String> generatePermutations(TicketSide ticketSide) {
        return getPermutationsNoRepetitions(ticketSide.getNumbers())
                .stream().map(perm -> perm.stream().map(String::valueOf).collect(Collectors.joining()))
                .toList();
    }
}
