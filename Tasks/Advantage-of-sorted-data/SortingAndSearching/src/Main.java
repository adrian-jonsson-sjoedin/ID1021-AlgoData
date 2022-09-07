import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //running benchmark for linear search for sorted and unsorted array
//        for (int i = 100; i < 100_000; i += 1000) {
////            System.out.println("array size = " + i + ", search time = " + benchmarkUnsortedSearch(i) + "ns");
////            System.out.printf("%d\t %f%n",i, benchmarkUnsortedSearch(i));
//            System.out.printf("%d\t %f%n", i, benchmarkSortedSearch(i));
//    }
//       }
//        System.out.printf("%f%n 16M",  benchmarkBinarySearch(64_000_000));


    }

    public static void duplicateSearch(int[] sortedArray1, int[] sortedArray2) {
        for (int i = 0; i < sortedArray1.length; i++) {
            binarySearch(sortedArray2,sortedArray1[i]);
        }
    }

    public static void betterDuplicateSearch(int[] sortedArray1, int[] sortedArray2) {
        
    }
    public static boolean binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = ((low + high) / 2);
            if (array[mid] == key) {
                return true;
            } else if (key > array[mid]) { //key is on the right side
                low = mid + 1;
            } else
                high = mid - 1; //key is on the left side.
        }
        return false;
    }
    public static double benchmarkBinarySearch(int maxArraySize) {
        Random rnd = new Random();
        int[] arrayToSearch = createSortedArray(maxArraySize);
        double sum = 0;
        for (int i = 0; i < 100_000; i++) {
            int key = rnd.nextInt(arrayToSearch.length);
            binarySearch(arrayToSearch, key);
            long timeStart = System.nanoTime();
            binarySearch(arrayToSearch, key);
            sum += (double) (System.nanoTime() - timeStart);
        }
        return sum / 100_000;
    }

    public static boolean linearSearch(int[] arrayToSearch, int key) {
        for (int i = 0; i < arrayToSearch.length; i++) {
            if (arrayToSearch[i] == key) {
                return true;
            }
        }
        return false;
    }

    public static boolean linearSortedSearch(int[] arrayToSearch, int key) {
        for (int i = 0; i < arrayToSearch.length; i++) {
            if (arrayToSearch[i] == key) {
                return true;
            }
            if (key < arrayToSearch[i]) {
                return false;
            }
        }
        return false;
    }

    /**
     * creates an array and measure the average time it takes to find a random element in the array when doing an unsorted
     * search
     *
     * @param maxArraySize the arrays size.
     * @return the average time measured over 1,000,000 loops
     */
    public static double benchmarkUnsortedSearch(int maxArraySize) {
        Random rnd = new Random();
        int[] arrayToSearch = createRandomArray(maxArraySize);
        double sum = 0;
        for (int i = 0; i < 100_000; i++) {
            int key = rnd.nextInt(arrayToSearch.length - 1);
            linearSearch(arrayToSearch, key);
            long timeStart = System.nanoTime();
            linearSearch(arrayToSearch, key);
            sum += (double) (System.nanoTime() - timeStart);
        }
        return sum / 100_000;
    }

    public static double benchmarkSortedSearch(int maxArraySize) {
        Random rnd = new Random();
        int[] arrayToSearch = createSortedArray(maxArraySize);
        double sum = 0;
        for (int i = 0; i < 100_000; i++) {
            int key = rnd.nextInt(arrayToSearch.length);
            linearSearch(arrayToSearch, key);
            long timeStart = System.nanoTime();
            linearSearch(arrayToSearch, key);
            sum += (double) (System.nanoTime() - timeStart);
        }
        return sum / 100_000;
    }

    public static int[] createSortedArray(int arraySize) {
        Random rnd = new Random();
        int[] array = new int[arraySize];
        int next = 0;
        for (int i = 0; i < array.length; i++) {
            next += (rnd.nextInt(10) + 1);
            array[i] = next;
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
