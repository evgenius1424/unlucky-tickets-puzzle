package com.github.evgenius1424;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Permutations {

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

    public static List<String> getPermutationsNoRepetitions(int[] arr) {
        return getPermutationsNoRepetitionsHelper(arr).stream().map(StringBuilder::toString).collect(Collectors.toList());
    }

    private static List<StringBuilder> getPermutationsNoRepetitionsHelper(int[] arr) {
        if (arr.length == 2) {
            StringBuilder sb1 = new StringBuilder();
            sb1.append(arr[0]);
            sb1.append(arr[1]);

            StringBuilder sb2 = new StringBuilder();
            sb2.append(arr[1]);
            sb2.append(arr[0]);

            return Arrays.asList(sb1, sb2);
        } else {
            List<StringBuilder> result = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                for (StringBuilder sb : getPermutationsNoRepetitionsHelper(excludingCopy(arr, i))) {
                    result.add(sb.insert(0, arr[i]));
                }
            }
            return result;
        }
    }

    private static int[] excludingCopy(int[] arr, int idx) {
        int[] result = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i != idx) {
                result[j++] = arr[i];
            }
        }
        return result;
    }
}