import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // int[] array = createRandomArray(400);
        // for (int i : array) {
        // System.out.printf("%d ", i);
        // }
        // System.out.println();
        // Sort.sort(array);
        // for (int i : array) {
        // System.out.printf("%d ", i);
        // }
        // System.out.println();

        // for (int i = 1000; i <= 10000; i += 2 * i) {
        // int[] array = createRandomArray(i);
        // System.out.printf("n");

        // }
        // System.out.printf("n\tSpace select\tinsert\tmerge\t %n");
        System.out.printf("%s\t %6s\t %14s\t %12s\t %6s\t %12s\t %n", "n", "select", "insert", "ratio", "merge",
                "ratio");
        for (int i = 100; i <= 25600; i = i * 2) {
            int[] array = createRandomArray(i);
            double selection = benchSelect(array, 10, 1000);
            double insert = benchInsert(array, 10, 1000);
            double merge = benchMerge(array, 10, 1000);
            double ratioSI = selection / insert;
            double ratioIM = insert / merge;
            System.out.printf("%d\t %.2f\t %.2f\t %.2f\t %.2f\t %.2f\t %n", i, selection, insert, ratioSI, merge,
                    ratioIM);

        }

    }

    public static double benchSelect(int[] array, int tries, int loop) {
        double minS = Double.POSITIVE_INFINITY;
        for (int k = 0; k < tries; k++) {
            double start = System.nanoTime();
            for (int j = 0; j < loop; j++) {
                int[] copy = array.clone();
                Sort.selection(copy);
            }
            double time = System.nanoTime() - start;
            if (time < minS) {
                minS = time;
            }
        }
        // System.out.println(minS / loop);
        return minS / loop;
    }

    public static double benchInsert(int[] array, int tries, int loop) {
        double min = Double.POSITIVE_INFINITY;
        for (int k = 0; k < tries; k++) {
            double start = System.nanoTime();
            for (int j = 0; j < loop; j++) {
                int[] copy = array.clone();
                Sort.insertion(copy);
            }
            double time = System.nanoTime() - start;
            if (time < min) {
                min = time;
            }
        }
        // System.out.println(min / loop);
        return min / loop;
    }

    public static double benchMerge(int[] array, int tries, int loop) {
        double min = Double.POSITIVE_INFINITY;
        for (int k = 0; k < tries; k++) {
            double start = System.nanoTime();
            for (int j = 0; j < loop; j++) {
                int[] copy = array.clone();
                Sort.sort(copy);
            }
            double time = System.nanoTime() - start;
            if (time < min) {
                min = time;
            }
        }
        // System.out.println(min / loop);
        return min / loop;
    }

    /**
     * creates an array of specified size with random numbers. Can contain
     * duplicates.
     * 
     * @param arraySize the desired size
     * @return an unsorted array.
     */
    private static int[] createRandomArray(int arraySize) {
        Random rnd = new Random();
        int[] rndArray = new int[arraySize];
        for (int i = 0; i < rndArray.length; i++) {
            rndArray[i] = rnd.nextInt(arraySize);
        }
        return rndArray;
    }

}
