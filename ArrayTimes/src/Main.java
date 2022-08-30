import java.util.Random;

public class Main {
    public static void main(String[] args) {
        callAccessWithLargerAndLargerN(1000);
    }

    public static void callAccessWithLargerAndLargerN(int n) {
        System.out.println("n went from 1 to " + n);
        double benchmarkTime = 0;
        for (int i = 1; i <= n; i++) {
            benchmarkTime = access(i);
            //System.out.println("For n = " + i + " we get the resolution " + benchmarkTime + "ns");
            System.out.println(benchmarkTime);
        }

    }

    public static void arraySumTime() {
        int[] given = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            long t0 = System.nanoTime();
            sum += given[i];
            long t1 = System.nanoTime();
            System.out.println(" resolution " + (t1 - t0) + " nanoseconds");
        }
    }

    public static void forLoopTime() {
        for (int i = 0; i < 10; i++) {
            long n0 = System.nanoTime();
            long n1 = System.nanoTime();
            System.out.println(" resolution " + (n1 - n0) + " nanoseconds");
        }
    }

    private static double access(int n) {
        int k = 1_000_000;
        int l = n;
        //creates an instance of Random to use for generating random numbers
        Random rnd = new Random();
        int[] indx = new int[l];
        // fill the indx array with random number from 0 to n (not including n)
        for (int i = 0; i < n; i++) {
            indx[i] = rnd.nextInt(n);
        }
        int[] array = new int[n];
// fill the array with dummy values (why not 1)
        for (int i = 0; i < n; i++) {
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
        return ((double) (t_access - t_dummy)) / ((double) k * (double) l);
    }
}