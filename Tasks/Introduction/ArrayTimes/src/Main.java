import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        callAccessWithLargerAndLargerN(1000);
        System.out.println("array size n = 10, nr of searches = 10 000: one element access takes "
                + access(10, 10_000) + "ns");
        System.out.println("array size n = 100, nr of searches = 10 000: one element access takes "
                + access(100, 10_000) + "ns");
        System.out.println("array size n = 1000, nr of searches = 10 000: one element access takes "
                + access(1000, 10_000) + "ns");
        System.out.println("array size n = 10 000, nr of searches = 10 000: one element access takes "
                + access(10_000, 10_000) + "ns");
        System.out.println("array size n = 50 000, nr of searches = 50 000: one element access takes "
                + access(50_000, 50_000) + "ns");
        System.out.println("array size n = 100 000, nr of searches = 10 000: one element access takes "
                + access(100_000, 50_000) + "ns");
    }


    private static double access(int arraySize, int nrOfSearches) {
        int k = 1_000_000;
        int l = nrOfSearches;
        //creates an instance of Random to use for generating random numbers
        Random rnd = new Random();
        int[] indx = new int[l];
        // fill the indx array with random number from 0 to n (not including n)
        for (int i = 0; i < l; i++) {
            indx[i] = rnd.nextInt(arraySize);
        }
        int[] array = new int[arraySize];
// fill the array with dummy values (why not 1)
        for (int i = 0; i < arraySize; i++) {
            array[i] = 1;
        }
        int sum = 0;
        //system time in ns when the arrays have been created. Used to establish a baseline time (i.e. zero)
        long t0 = System.nanoTime();
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < l; i++) {
// access the array with the index given by indx[i]
// sum up the result
                sum += array[indx[i]];
            }
        }
        long t_access = (System.nanoTime() - t0);
        t0 = System.nanoTime();
        int dummy = 0;
// do the same loop iteration but only do a dummy add operation
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < l; i++) {
                dummy = i + 1;
            }
        }
        long t_dummy = (System.nanoTime() - t0);
        return ((double) (t_access - t_dummy)) / ((double) k * (double) l); // divide by l to get the time for one element
        //access
    }
}