package com.github.evgenius1424;

import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TicketSide {

    private final List<Integer> numbers;
    private final Collection<Integer> values;

    public TicketSide(List<Integer> numbers) {
        this.numbers = numbers;
        this.values = ArithmeticEvaluator.evaluate(numbers);
    }

    @Override
    public String toString() {
        return numbers.stream().map(String::valueOf).collect(Collectors.joining());
    }
}