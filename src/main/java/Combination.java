import java.util.*;

public class Combination
{
    private int x, y, z;
    private Set<Integer> values;

    public Combination(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        values = new TreeSet<>();

        int[] arr = {x, y, z};
        Set<String> permutations = new HashSet<>(PermutationsNoRepetition.getPermutationsList(arr));
        countValuesForAllPermutations(permutations);
    }

    private void countValuesForAllPermutations(Collection<String> permutations)
    {
        for (String permutation : permutations)
        {
            int a, b, c;
            a = Character.getNumericValue(permutation.charAt(0));
            b = Character.getNumericValue(permutation.charAt(1));
            c = Character.getNumericValue(permutation.charAt(2));
            countValues(a, b, c);
        }
    }

    private void countValues(int a, int b, int c)
    {
        //Sum
        addValue(a + b + c);
        addValue(a + b - c);
        addValue(a + b * c);
        addValue((a + b) * c);

        if (!isZero(c) && isQuotientInt(b, c))
        {
            addValue(a + b / c);
        }
        if (!isZero(c) && isQuotientInt(a + b, c))
        {
            addValue((a + b) / c);
        }

        //Subtraction
        addValue(a - b + c);
        addValue(a - b - c);
        addValue(a - b * c);
        addValue((a - b) * c);

        if (!isZero(c) && isQuotientInt(b, c))
        {
            addValue(a - b / c);
        }
        if (!isZero(c) && isQuotientInt(a - b, c))
        {
            addValue((a - b) / c);
        }

        //Multiplication
        addValue(a * b + c);
        addValue(a * b - c);
        addValue(a * (b - c));
        addValue(a * b * c);

        if (!isZero(c) && isQuotientInt(a * b, c))
        {
            addValue(a * b / c);
        }

        //Division.Sum
        if (!isZero(b) && isQuotientInt(a, b))
        {
            addValue(a / b + c);
        }
        if (!isZero(b + c) && isQuotientInt(a, b + c))
        {
            addValue(a / (b + c));
        }

        //Division.Subtraction
        if (!isZero(b) && isQuotientInt(a, b))
        {
            addValue(a / b - c);
        }
        if (!isZero(b - c) && isQuotientInt(a, b - c))
        {
            addValue(a / (b - c));
        }

        //Division.Multiplication
        if (!isZero(b) && isQuotientInt(a, b))
        {
            addValue(a / b * c);
        }
        if (!isZero(b * c) && isQuotientInt(a, (b * c)))
        {
            addValue(a / (b * c));
        }

        //Division.Division
        if (!isZero(b) && !isZero(c) && isQuotientInt(a, b) && isQuotientInt(b, c))
        {
            addValue(a / b / c);
        }
    }


    private void addValue(int value)
    {
        if (value > -1) { values.add(value); }
    }

    private boolean isZero(int x)
    {
        return x == 0;
    }

    private boolean isQuotientInt(int a, int b)
    {
        return isZero(a % b);
    }

    public Set<Integer> getValues()
    {
        return values;
    }

    @Override
    public String toString()
    {
        return String.valueOf(x) + String.valueOf(y) + String.valueOf(z);
    }
}
