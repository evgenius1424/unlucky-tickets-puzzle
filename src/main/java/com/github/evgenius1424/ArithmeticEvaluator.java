package com.github.evgenius1424;

import java.util.*;

import static com.github.evgenius1424.PermutationUtils.getPermutationsNoRepetitions;
import static com.github.evgenius1424.ReversePolishNotationUtils.evaluateRPN;

public class ArithmeticEvaluator {

    /**
     * Calculates all possible values that can be produced by applying arithmetic operations (+, -, *, /) to the given numbers.
     * Numbers can be permuted in any order.
     * <p>
     * Parentheses can be used to change the order of operations.
     * <p>
     * Division should always produce an integer result.
     */
    public static Set<Integer> evaluate(List<Integer> numbers) {
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

        return values;
    }

    private static List<String> generateRPN(List<Integer> nums, int start, int end) {
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
}
