package com.github.evgenius1424;

import java.util.Collection;
import java.util.HashSet;

import static com.github.evgenius1424.Permutations.getPermutationsNoRepetitions;

public class Combination {
    private final int x, y, z;
    private final Collection<Integer> values = new HashSet<>();

    public Combination(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        initValues();
    }

    private void initValues() {
        getPermutationsNoRepetitions(new int[]{x, y, z}).forEach(permutation -> {
            int a = permutation.charAt(0);
            int b = permutation.charAt(1);
            int c = permutation.charAt(2);

            val(a + b + c);
            val(a + b - c);
            val(a + b * c);
            val((a + b) * c);

            if (canDivide(b, c)) {
                val(a + b / c);
            }
            if (canDivide(a + b, c)) {
                val((a + b) / c);
            }

            val(a - b + c);
            val(a - b - c);
            val(a - b * c);
            val((a - b) * c);

            if (canDivide(b, c)) {
                val(a - b / c);
            }
            if (canDivide(a - b, c)) {
                val((a - b) / c);
            }

            val(a * b + c);
            val(a * (b + c));

            val(a * b - c);
            val(a * (b - c));

            val(a * b * c);

            if (canDivide(a * b, c)) {
                val(a * b / c);
            }

            if (canDivide(a, b)) {
                val(a / b + c);
            }
            if (canDivide(a, b + c)) {
                val(a / (b + c));
            }

            if (canDivide(a, b)) {
                val(a / b - c);
            }
            if (canDivide(a, b - c)) {
                val(a / (b - c));
            }

            if (canDivide(a, b)) {
                val(a / b * c);
            }
            if (canDivide(a, (b * c))) {
                val(a / (b * c));
            }

            if (canDivide(a, b) && canDivide(b, c)) {
                val(a / b / c);
            }
        });
    }

    public Collection<Integer> getValues() {
        return values;
    }


    private void val(int value) {
        if (value > -1) values.add(value);
    }

    private boolean canDivide(int a, int b) {
        return b != 0 && a % b == 0;
    }

    @Override
    public String toString() {
        return "" + x + y + z;
    }
}
