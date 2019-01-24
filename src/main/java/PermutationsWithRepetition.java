class PermutationsWithRepetition {
    private int[] source;
    private int variationLength;

    PermutationsWithRepetition(int[] source, int variationLength) {
        this.source = source;
        this.variationLength = variationLength;
    }

    int[][] getPermutations() {
        int srcLength = source.length;
        int permutations = (int)Math.pow(srcLength, variationLength);

        int[][] table = new int[permutations][variationLength];

        for (int i = 0; i < variationLength; i++) {
            int t2 = (int)Math.pow(srcLength, i);
            for (int p1 = 0; p1 < permutations; ) {
                for (int a : source) {
                    for (int p2 = 0; p2 < t2; p2++) {
                        table[p1][i] = a;
                        p1++;
                    }
                }
            }
        }
        return table;
    }
}