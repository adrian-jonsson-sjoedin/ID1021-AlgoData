import java.util.Random;

public class Main {
    public static void main(String[] args) {
//          benchmarkArrayElementAccess(1000, 100_000);
//        System.out.println(searchTime(1000000));
//        benchmarkSearchTime(10_000);
        benchmarkDuplicateTime(100000);
    }

    /**
     * Method that let us run the random array element access for arrays up to size n. Increments in array size of 10.
     *
     * @param n     maximum array size
     * @param loops number of loops per element access.
     */
    public static void benchmarkArrayElementAccess(int n, int loops) {
        int j = 1;
        for (int i = 1; i <= n; i = 10 * j) {
            j++;
            randomArrayElementAccessTime(n, loops);
            System.out.println("n = " + i + "--> " + randomArrayElementAccessTime(i, loops) + "ns");
        }
    }

    private static void benchmarkSearchTime(int n) {
        int j = 0;
        for (int i = 1; i <= n; i = 10 * j) {
            j++;
//            searchTime(i);
            System.out.println("n = " + i + "--> " + searchTime(i) + "ns");
//            System.out.println(searchTime(i));
        }
    }

    private static void benchmarkDuplicateTime(int n) {
        int j = 0;
        for (int i = 1; i <= n; i = 100 * j) {
            j++;
//            searchTime(i);
            System.out.println("n = " + i + "--> " + duplicateTime(i) + "ms");
//            System.out.println(duplicateTime(i));
        }
    }

    /**
     * measures the time it takes to access one random element in an array
     *
     * @param arraySize the size of the array
     * @param loops     the number of times we run the access loop. More gives a better result
     * @return the average time for one random element access returned as a double.
     */
    private static double randomArrayElementAccessTime(int arraySize, int loops) {
        int[] randomArray = randomArray(arraySize);
        int[] randomArrayIndex = randomArraysIndex(randomArray);
        int sum = 0;
        long t0 = System.nanoTime();
        for (int j = 0; j < loops; j++) {
            for (int i = 0; i < arraySize; i++) {
                sum += randomArray[randomArrayIndex[i]];
            }
        }
        long tAccess = (System.nanoTime() - t0);
        //add dummy loop to be able to subtract the time it takes for the program to do the loop, leaving just the time
        //it takes for the array access.
        t0 = System.nanoTime();
        int dummy = 0;
        for (int j = 0; j < loops; j++) {
            for (int i = 0; i < arraySize; i++) {
                dummy += 1;
            }
        }
        long tDummy = (System.nanoTime() - t0);
        return ((double) (tAccess - tDummy) / ((double) arraySize * (double) loops));
    }

    /**
     * Creates an integer array populated by random values up to 10 times the length of the array
     *
     * @param arraySize is the length of the array
     * @return an integer array
     */
    private static int[] randomArray(int arraySize) {
        Random rnd = new Random();
        int[] array = new int[arraySize];
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(10 * arraySize);
        }
        return array;
    }

    /**
     * Creates an array whose element values are the index for another array but in random order
     *
     * @param randomArray the array for which we want to create an index array
     * @return an integer array
     */
    private static int[] randomArraysIndex(int[] randomArray) {
        int[] index = new int[randomArray.length];
        Random rnd = new Random();
        for (int j = 0; j < index.length; j++) {
            index[j] = rnd.nextInt(randomArray.length);
        }
        return index;
    }


    private static double searchTime(int n) {
        int k = 1000;
        int m = 100_000;

        int[] keys = new int[m];
        int[] array = new int[n];
        Random rnd = new Random();
        //populate the key with random values
        for (int j = 0; j < keys.length; j++) {
            keys[j] = rnd.nextInt(n * 10);
        }
        //populate the array with random values
        for (int i = 1; i < array.length; i++)
            array[i] = rnd.nextInt(n * 10);
        long t0 = System.nanoTime();
        for (int ki = 0; ki < m; ki++) {
            int key = keys[ki];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (array[i] == key) {
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime();
        long t_total = t1 - t0;
        t_total += (System.nanoTime() - t0);
        return (((double) t_total) / ((double) k * (double) m));
    }

    private static double duplicateTime(int n) {
        int[] keys = new int[n];
        int[] array = new int[n];
        Random rnd = new Random();

        //populate the key with random values
        for (int j = 0; j < keys.length; j++) {
            keys[j] = rnd.nextInt(n * 10);
        }
        //populate the array with random values
        for (int i = 1; i < array.length; i++)
            array[i] = rnd.nextInt(n * 10);
        int sum = 0;
        long t0 = System.nanoTime();
        for (int ki = 0; ki < n; ki++) {
            int check = keys[ki];
            for (int i = 0; i < n; i++) {
                if (array[i] == check) {
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime();
        long t_total = t1 - t0;
        t_total += (System.nanoTime() - t0);
//        return ((double)t_total/((double)n*(double)n));
    return ((double) t_total) / 1000;
    }

}