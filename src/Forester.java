public class Forester {

    private int[] forest;
    private int range;

    public Forester(int[] forest, int range) {
        this.forest = forest;
        this.range = range;
    }

    public int[] count() {
        int[] counter = new int[this.range];
        for (int i = 0; i < forest.length; i++) {
            counter[forest[i] - 1]++;
        }

        return counter;
    }
}
