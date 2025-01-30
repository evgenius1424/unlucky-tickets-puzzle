package com.github.evgenius1424;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.evgenius1424.PermutationUtils.getPermutationsNoRepetitions;

public class TicketSide {
    private final Collection<Integer> values = new HashSet<>();

    public List<Integer> getNumbers() {
        return numbers;
    }

    private final List<Integer> numbers;

    public TicketSide(List<Integer> numbers) {
        this.numbers = numbers;
        List<List<Integer>> permutations = getPermutationsNoRepetitions(numbers);
        for (List<Integer> permutation : permutations) {
            generateRPNValues(permutation);
        }
    }

    public Collection<Integer> getValues() {
        return values;
    }

    private void generateRPNValues(List<Integer> nums) {
        List<String> rpnExpressions = generateAllRPNExpressions(nums);
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

    private List<String> generateAllRPNExpressions(List<Integer> nums) {
        return generateRPN(nums, 0, nums.size() - 1);
    }

    private List<String> generateRPN(List<Integer> nums, int start, int end) {
        List<String> expressions = new ArrayList<>();
        if (start == end) {
            expressions.add(Integer.toString(nums.get(start)));
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

    public static void main(String[] args) {
        TicketSide comb = new TicketSide(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println(comb.getValues());
    }
}