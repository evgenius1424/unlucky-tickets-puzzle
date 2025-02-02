package com.github.evgenius1424;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.evgenius1424.PermutationUtils.getPermutationsNoRepetitions;
import static com.github.evgenius1424.ReversePolishNotationUtils.evaluateRPN;

@Getter
public class TicketSide {

    private final List<Integer> numbers;
    private final Collection<Integer> values;

    public TicketSide(List<Integer> numbers) {
        var values = new HashSet<Integer>();
        var permutations = getPermutationsNoRepetitions(numbers);
        for (List<Integer> permutation : permutations) {
            List<String> rpnExpressions = generateRPN(permutation, 0, permutation.size() - 1);
            for (String expression : rpnExpressions) {
                try {
                    int result = evaluateRPN(expression);
                    if (result >= 0) {
                        values.add(result);
                    }
                } catch (ArithmeticException ignored) {
                }
            }
        }

        this.numbers = numbers;
        this.values = values;
    }

    private List<String> generateRPN(List<Integer> nums, int start, int end) {
        var expressions = new ArrayList<String>();
        if (start == end) {
            expressions.add(Integer.toString(nums.get(start)));
            return expressions;
        }
        for (int i = start; i < end; i++) {
            var leftExpressions = generateRPN(nums, start, i);
            var rightExpressions = generateRPN(nums, i + 1, end);
            for (String left : leftExpressions) {
                for (String right : rightExpressions) {
                    for (String op : Arrays.asList("+", "-", "*", "/")) {
                        String s = left + " " + right + " " + op;
                        expressions.add(s);
                    }
                }
            }
        }
        return expressions;
    }


    @Override
    public String toString() {
        return numbers.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        TicketSide comb = new TicketSide(Arrays.asList(1, 2, 3));
        System.out.println(comb.getValues());
    }
}