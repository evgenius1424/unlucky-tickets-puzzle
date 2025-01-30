package com.github.evgenius1424;

import java.util.*;

public class PermutationUtils {
    public static <T> List<List<T>> getPermutationsNoRepetitions(List<T> items) {
        List<List<T>> permutations = new ArrayList<>();
        permute(new ArrayList<>(items), 0, permutations, new HashSet<>());
        return permutations;
    }

    private static <T> void permute(List<T> list, int start, List<List<T>> permutations, Set<String> seen) {
        if (start == list.size()) {
            String key = list.toString();
            if (!seen.contains(key)) {
                seen.add(key);
                permutations.add(new ArrayList<>(list));
            }
            return;
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            permute(list, start + 1, permutations, seen);
            Collections.swap(list, start, i);
        }
    }
}