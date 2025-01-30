package com.github.evgenius1424;

import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class ReversePolishNotationUtils {
    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = Map.of(
            "+", Integer::sum,
            "-", (a, b) -> a - b,
            "*", (a, b) -> a * b,
            "/", (a, b) -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                if (a % b != 0) {
                    throw new ArithmeticException("Non-integer intermediate results are not supported due to custom evaluation rules");
                }
                return a / b;
            }
    );

    public static int evaluateRPN(String expression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.split(" ");
        try {
            for (String token : tokens) {
                if (OPERATIONS.containsKey(token)) {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(OPERATIONS.get(token).apply(a, b));
                } else {
                    stack.push(Integer.parseInt(token));
                }
            }
        } catch (EmptyStackException e) {
            throw new ArithmeticException("Invalid RPN expression: not enough operands for operation");
        }

        if (stack.size() != 1) {
            throw new ArithmeticException("Invalid RPN expression: extra operands left in the stack");
        }
        return stack.pop();
    }
}
