import java.io.*;
import java.util.*;

public class Application
{
    public static void main(String[] args) throws FileNotFoundException
    {
        long time = System.currentTimeMillis();

        List<Combination> combinations = createCombinations();
        Set<String> tickets = createUnluckyTicketsFromCombinations(combinations);

        System.out.println("Time: " + String.valueOf(System.currentTimeMillis() - time));
        System.out.println("Tickes: " + tickets.size());
    }

    private static void saveTicketsToFile(Collection<String> tickets) throws FileNotFoundException
    {
        PrintWriter out = new PrintWriter("Tickets.txt");
        out.println(tickets);
        out.close();
    }

    private static List<Combination> createCombinations()
    {
        List<Combination> combinations = new ArrayList<>(1000);

        PermutationsWithRepetition permutationUtil = new PermutationsWithRepetition(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 3);
        int[][] permutations = permutationUtil.getPermutations();
        for (int[] x : permutations)
        {
            combinations.add(new Combination(x[0], x[1], x[2]));
        }

        return combinations;
    }

    private static Set<String> createUnluckyTicketsFromCombinations(List<Combination> combinations)
     {
        Set<String> tickets = new TreeSet<>();

        for (Combination comb1 : combinations)
        {
            for (Combination comb2 : combinations)
            {
                for (Integer x : comb1.getValues())
                {
                    if (comb2.getValues().contains(x))
                    {
                        tickets.remove(comb1.toString() + comb2.toString());
                        break;
                    }
                    else
                    {
                        tickets.add(comb1.toString() + comb2.toString());
                    }
                }
            }
        }

        return tickets;
    }
}

