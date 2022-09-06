import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //running benchmark test of unsorted array
        for (int i = 100; i < 100_000; i += 100) {
//            System.out.println("array size = " + i + ", search time = " + benchmarkUnsortedSearch(i) + "ns");
            System.out.printf("%d\t %f%n",i, benchmarkUnsortedSearch(i));
//            System.out.println(benchmarkUnsortedSearch(i));
        }
    }
    public static boolean searchUnsorted(int[] arrayToSearch, int key) {
        for (int i = 0; i < arrayToSearch.length; i++) {
            if (arrayToSearch[i] == key) {
                return true;
            }
        }
        return false;
    }

    /**
     * creates an array and measure the average time it takes to find a random element in the array when doing an unsorted
     * search
     * @param maxArraySize the arrays size.
     * @return the average time measured over 1,000,000 loops
     */
    public static double benchmarkUnsortedSearch(int maxArraySize) {
        Random rnd = new Random();
        int[] arrayToSearch = createRandomArray(maxArraySize);
        double sum = 0;
        for (int i = 0; i < 100_000; i++) {
            int key = rnd.nextInt(100_000);
            long timeStart = System.nanoTime();
            searchUnsorted(arrayToSearch, key);
            sum += (double) (System.nanoTime() - timeStart) ;
        }
        return sum / 100_000;
    }

    public static int[] createSortedArray(int arraySize) {
        Random rnd = new Random();
        int[] array = new int[arraySize];
        for (int i = 0; i < array.length; i++) {
            array[i] = (rnd.nextInt(10)+1);
        }
        return array;
    }

    public static int[] createRandomArray(int maxArraySize) {
        Random rnd = new Random();
        int[] rndArray = new int[maxArraySize];
        for (int i = 0; i < rndArray.length; i++) {
            rndArray[i] = rnd.nextInt(100_000);
        }
        return rndArray;
    }

}
