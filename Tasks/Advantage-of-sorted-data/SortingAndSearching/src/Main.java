import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //running benchmark test of unsorted array
        for (int i = 100; i < 1000000; i += 100) {
            System.out.println("array size = " + i + ", search time = " + benchmarkUnsortedSearch(i) + "ns");
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
        int[] arrayToSearch = new int[maxArraySize];
        double sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            int key = rnd.nextInt(maxArraySize);
            long timeStart = System.nanoTime();
            searchUnsorted(arrayToSearch, key);
            sum += (double) (System.nanoTime() - timeStart) ;
        }
        return sum / 1000000;
    }


}
