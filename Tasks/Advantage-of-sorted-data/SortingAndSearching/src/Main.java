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

        //testing boundary values for better duplicate search
//        int[] a = new int[]{1, 2, 4, 6};
//        int[] b = new int[]{2, 3, 5,6,7,8};
//        System.out.printf("first array: ");
//        for (int i = 0; i < a.length; i++) {
//            System.out.printf("%d ", a[i]);
//        }
//        System.out.println();
//        System.out.printf("second array: ");
//        for (int i = 0; i < b.length; i++) {
//            System.out.printf("%d ", b[i]);
//        }
//        System.out.println();
//        betterDuplicateSearch(a, b);

        //benchmark of the duplicate search
        System.out.printf("%d\t %f%n", 100, benchmarkDuplicateSearch(100, 100));
        System.out.printf("%d\t %f%n", 200, benchmarkDuplicateSearch(200, 200));
        System.out.printf("%d\t %f%n", 400, benchmarkDuplicateSearch(400, 400));
        System.out.printf("%d\t %f%n", 800, benchmarkDuplicateSearch(800, 800));
        System.out.printf("%d\t %f%n", 1600, benchmarkDuplicateSearch(1600, 1600));
        System.out.printf("%d\t %f%n", 3200, benchmarkDuplicateSearch(3200, 3200));
        System.out.printf("%d\t %f%n", 6400, benchmarkDuplicateSearch(6400, 6400));
        System.out.printf("%d\t %f%n", 12800, benchmarkDuplicateSearch(12800, 12800));

        //benchmark better duplicate search
//        System.out.printf("%d\t %f%n", 100, benchmarkBetterDuplicateSearch(100,100));
//        System.out.printf("%d\t %f%n", 200, benchmarkBetterDuplicateSearch(200,200));
//        System.out.printf("%d\t %f%n", 400, benchmarkBetterDuplicateSearch(400,400));
//        System.out.printf("%d\t %f%n", 800, benchmarkBetterDuplicateSearch(800,800));
//        System.out.printf("%d\t %f%n", 1600, benchmarkBetterDuplicateSearch(1600,1600));
//        System.out.printf("%d\t %f%n", 3200, benchmarkBetterDuplicateSearch(3200,3200));
//        System.out.printf("%d\t %f%n", 6400, benchmarkBetterDuplicateSearch(6400,6400));
//        System.out.printf("%d\t %f%n", 12800, benchmarkBetterDuplicateSearch(12800,12800));

    }

    public static void duplicateSearch(int[] sortedArray1, int[] sortedArray2) {
        for (int i = 0; i < sortedArray1.length; i++) {
            binarySearch(sortedArray2, sortedArray1[i]);
        }
    }

    public static void betterDuplicateSearch(int[] sortedArray1, int[] sortedArray2) {
        int index1 = 0;
        int index2 = 0;
        while (index1 < (sortedArray1.length) && index2 < sortedArray2.length) {
            if (sortedArray2[index2] < sortedArray1[index1]) {
                index2++;
            } else if (sortedArray1[index1] == sortedArray2[index2]) {
                index1++;
//                System.out.println("duplicate found! " + sortedArray1[index1 - 1] + sortedArray2[index2]);

            } else if (sortedArray1[index1] < sortedArray2[index2]) {
                index1++;
            }
//            System.out.println("no more duplicates");
//            System.out.println(index1);
        }
    }

    public static double benchmarkDuplicateSearch(int arraySize1, int arraySize2) {
        int[] sortedArray1 = createSortedArray(arraySize1);
        int[] sortedArray2 = createSortedArray(arraySize2);
        double sum = 0;
        for (int i = 0; i < 100_000; i++) {
            // duplicateSearch(sortedArray1,sortedArray2);
            long timeStart = System.nanoTime();
            duplicateSearch(sortedArray1, sortedArray2);
            sum += (double) (System.nanoTime() - timeStart);
        }
        return sum / 100_000;
    }

    public static double benchmarkBetterDuplicateSearch(int arraySize1, int arraySize2) {
        int[] sortedArray1 = createSortedArray(arraySize1);
        int[] sortedArray2 = createSortedArray(arraySize2);
        double sum = 0;
        for (int i = 0; i < 100_000; i++) {
//             betterDuplicateSearch(sortedArray1,sortedArray2);
            long timeStart = System.nanoTime();
            betterDuplicateSearch(sortedArray1, sortedArray2);
            sum += (double) (System.nanoTime() - timeStart);
        }
        return sum / 100_000;
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
