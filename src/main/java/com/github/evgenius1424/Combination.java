package com.github.evgenius1424;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Combination {
    private final Collection<Integer> values = new HashSet<>();
    private final List<Integer> numbers;

    public Combination(List<Integer> numbers) {
        this.numbers = numbers;
        List<int[]> permutations = getPermutationsNoRepetitions(numbers);
        for (int[] permutation : permutations) {
            generateRPNValues(permutation);
        }
    }

    public Collection<Integer> getValues() {
        return values;
    }

    private void generateRPNValues(int[] nums) {
        List<String> rpnExpressions = generateAllRPNExpressions(nums);
        for (String expression : rpnExpressions) {
            try {
                int result = evaluateRPN(expression);
                if (result >= 0) {
                    values.add(result);
                }
            } catch (ArithmeticException e) {
                // Ignore invalid expressions
            }
        }
    }

    private List<String> generateAllRPNExpressions(int[] nums) {
        return generateRPN(nums, 0, nums.length - 1);
    }

    private List<String> generateRPN(int[] nums, int start, int end) {
        List<String> expressions = new ArrayList<>();
        if (start == end) {
            expressions.add(Integer.toString(nums[start]));
            return expressions;
        }
        for (int i = start; i < end; i++) {
            List<String> leftExpressions = generateRPN(nums, start, i);
            List<String> rightExpressions = generateRPN(nums, i + 1, end);
            for (String left : leftExpressions) {
                for (String right : rightExpressions) {
                    Stream.of("+", "-", "*", "/")
                            .map(op -> left + " " + right + " " + op)
                            .forEach(expressions::add);
                }
            }
        }
        return expressions;
    }

    private int evaluateRPN(String expression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            switch (token) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    if (divisor == 0 || dividend % divisor != 0) {
                        throw new ArithmeticException("Invalid division");
                    }
                    stack.push(dividend / divisor);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        if (stack.size() != 1) {
            throw new ArithmeticException("Invalid RPN expression");
        }
        return stack.pop();
    }

    @Override
    public String toString() {
        return numbers.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private static List<int[]> getPermutationsNoRepetitions(List<Integer> numbers) {
        List<int[]> permutations = new ArrayList<>();
        permute(numbers.stream().mapToInt(i -> i).toArray(), 0, permutations, new HashSet<>());
        return permutations;
    }

    private static void permute(int[] arr, int start, List<int[]> permutations, Set<String> seen) {
        if (start == arr.length) {
            String key = Arrays.toString(arr);
            if (!seen.contains(key)) {
                seen.add(key);
                permutations.add(Arrays.copyOf(arr, arr.length));
            }
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            permute(arr, start + 1, permutations, seen);
            swap(arr, start, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Combination comb = new Combination(Arrays.asList(1, 2, 3));
        System.out.println(comb.getValues());
    }
}