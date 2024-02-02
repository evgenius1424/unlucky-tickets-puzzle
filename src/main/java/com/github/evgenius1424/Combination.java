package com.github.evgenius1424;

import java.util.Collection;
import java.util.HashSet;

import static com.github.evgenius1424.Permutations.getPermutationsNoRepetitions;

public class Combination {
    private final int x, y, z;
    private final Collection<Integer> values;

    Combination(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        values = new HashSet<>();
        countValuesForAllPermutations();
    }

    private void countValuesForAllPermutations() {
        for (String permutation : getPermutationsNoRepetitions(new int[]{x, y, z})) {
            int a, b, c;
            a = Character.getNumericValue(permutation.charAt(0));
            b = Character.getNumericValue(permutation.charAt(1));
            c = Character.getNumericValue(permutation.charAt(2));
            countValues(a, b, c);
        }
    }

    private void countValues(int a, int b, int c) {
        //Sum
        addValue(a + b + c);
        addValue(a + b - c);
        addValue(a + b * c);
        addValue((a + b) * c);

        if (!isZero(c) && isQuotientInt(b, c)) {
            addValue(a + b / c);
        }
        if (!isZero(c) && isQuotientInt(a + b, c)) {
            addValue((a + b) / c);
        }

        //Subtraction
        addValue(a - b + c);
        addValue(a - b - c);
        addValue(a - b * c);
        addValue((a - b) * c);

        if (!isZero(c) && isQuotientInt(b, c)) {
            addValue(a - b / c);
        }
        if (!isZero(c) && isQuotientInt(a - b, c)) {
            addValue((a - b) / c);
        }

        //Multiplication
        addValue(a * b + c);
        addValue(a * (b + c));

        addValue(a * b - c);
        addValue(a * (b - c));

        addValue(a * b * c);

        if (!isZero(c) && isQuotientInt(a * b, c)) {
            addValue(a * b / c);
        }

        //Division.Sum
        if (!isZero(b) && isQuotientInt(a, b)) {
            addValue(a / b + c);
        }
        if (!isZero(b + c) && isQuotientInt(a, b + c)) {
            addValue(a / (b + c));
        }

        //Division.Subtraction
        if (!isZero(b) && isQuotientInt(a, b)) {
            addValue(a / b - c);
        }
        if (!isZero(b - c) && isQuotientInt(a, b - c)) {
            addValue(a / (b - c));
        }

        //Division.Multiplication
        if (!isZero(b) && isQuotientInt(a, b)) {
            addValue(a / b * c);
        }
        if (!isZero(b * c) && isQuotientInt(a, (b * c))) {
            addValue(a / (b * c));
        }

        //Division.Division
        if (!isZero(b) && !isZero(c) && isQuotientInt(a, b) && isQuotientInt(b, c)) {
            addValue(a / b / c);
        }
    }


    private void addValue(int value) {
        if (value > -1) {
            values.add(value);
        }
    }

    private boolean isZero(int x) {
        return x == 0;
    }

    private boolean isQuotientInt(int a, int b) {
        return isZero(a % b);
    }

    Collection<Integer> getValues() {
        return values;
    }


    @Override
    public String toString() {
        return "" + x + y + z;
    }
}
