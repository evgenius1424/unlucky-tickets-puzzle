package com.github.evgenius1424;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PermutationUtilsTest {

    @Test
    void getPermutationsNoRepetitionsAllNumbersAreDifferent() {
        List<List<Integer>> actual = PermutationUtils.getPermutationsNoRepetitions(List.of(1, 2, 3));
        assertThat(actual)
                .containsExactlyInAnyOrder(
                        List.of(1, 2, 3),
                        List.of(1, 3, 2),
                        List.of(2, 1, 3),
                        List.of(2, 3, 1),
                        List.of(3, 1, 2),
                        List.of(3, 2, 1)
                );
    }

    @Test
    void getPermutationsNoRepetitionsDuplicatedNumbers() {
        List<List<Integer>> actual = PermutationUtils.getPermutationsNoRepetitions(List.of(1, 1, 2));
        assertThat(actual)
                .containsExactlyInAnyOrder(
                        List.of(1, 1, 2),
                        List.of(1, 2, 1),
                        List.of(2, 1, 1)
                );
    }

    @Test
    void getPermutationsNoRepetitionsAllEqualNumbers() {
        List<List<Integer>> actual = PermutationUtils.getPermutationsNoRepetitions(List.of(1, 1, 1));
        assertThat(actual)
                .containsExactly(List.of(1, 1, 1));
    }
}