import java.util.Random;

public class Main {
    public static void main(String[] args) {
      //  System.out.println(randomArrayElementAccess(10000, 1000000));
    benchmark(1000,100000);
    }

    public static void benchmark(int n, int loops) {
        int j = 1;
        for (int i = 1; i <= n; i = 10 * j) {
            j++;
            randomArrayElementAccess(n,loops);
            System.out.println("n = " + i + "--> " + randomArrayElementAccess(n, loops) + "ns");
        }
    }

    private static double randomArrayElementAccess(int arraySize, int loops) {
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


    private static double searchTime(int nrOfRounds, int nrOfSearchOperationsPerRound, int arraySize) {
        int[] keys = new int[nrOfSearchOperationsPerRound];
        int[] array = new int[arraySize];
        Random rnd = new Random();
        //fill the two arrays with random numbers
        for (int j = 0; j < keys.length; j++) {
            keys[j] = rnd.nextInt(10 * arraySize);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(10 * arraySize);
        }
        int sum = 0;
        long t0 = System.nanoTime();
        long tTotal = 0;
        for (int k = 0; k < nrOfSearchOperationsPerRound; k++) {
            int key = keys[k];
            for (int i = 0; i < arraySize; i++) {
                if (array[i] == key) {
                    sum++;
                    break;
                }
            }
        }
        tTotal += (System.nanoTime() - t0);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        return ((double) tTotal / (nrOfSearchOperationsPerRound * nrOfRounds));
    }
}