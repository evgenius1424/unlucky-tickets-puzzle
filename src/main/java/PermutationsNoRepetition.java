import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PermutationsNoRepetition {
    static List<String> getPermutationsList(int[] arr) {
        List<String> l = new ArrayList<>();
        for (StringBuilder sb : getPermutations(arr)) {
            l.add(sb.toString());
        }
        return l;
    }

    private static List<StringBuilder> getPermutations(int[] arr) {
        if (arr.length == 2) {
            StringBuilder sb1 = new StringBuilder();
            sb1.append(arr[0]);
            sb1.append(arr[1]);

            StringBuilder sb2 = new StringBuilder();
            sb2.append(arr[1]);
            sb2.append(arr[0]);

            return Arrays.asList(sb1, sb2);
        }
        else {
            List<StringBuilder> l = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {

                for (StringBuilder sb : getPermutations(excludingCopy(arr, i))) {
                    l.add(sb.insert(0, arr[i]));
                }
            }
            return l;
        }
    }

    private static int[] excludingCopy(int[] arr, int idx) {
        int[] result = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i != idx) {
                result[j++] = arr[i];
            }
        }
        return result;
    }
}