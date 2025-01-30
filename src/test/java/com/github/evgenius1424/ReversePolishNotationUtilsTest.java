package com.github.evgenius1424;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReversePolishNotationUtilsTest {

    @Test
    void evaluateRPN_addition() {
        int result = ReversePolishNotationUtils.evaluateRPN("3 4 +");
        assertEquals(7, result);
    }

    @Test
    void evaluateRPN_subtraction() {
        int result = ReversePolishNotationUtils.evaluateRPN("10 4 -");
        assertEquals(6, result);
    }

    @Test
    void evaluateRPN_multiplication() {
        int result = ReversePolishNotationUtils.evaluateRPN("2 3 *");
        assertEquals(6, result);
    }

    @Test
    void evaluateRPN_division() {
        int result = ReversePolishNotationUtils.evaluateRPN("8 2 /");
        assertEquals(4, result);
    }

    @Test
    void evaluateRPN_invalidDivision() {
        assertThrows(ArithmeticException.class, () -> ReversePolishNotationUtils.evaluateRPN("8 0 /"));
    }

    @Test
    void evaluateRPN_invalidExpression() {
        assertThrows(ArithmeticException.class, () -> ReversePolishNotationUtils.evaluateRPN("3 4 + +"));
    }

    @Test
    void evaluateRPN_invalidExpression_extraOperands() {
        assertThrows(ArithmeticException.class, () -> ReversePolishNotationUtils.evaluateRPN("3 4 5 +"));
    }
}