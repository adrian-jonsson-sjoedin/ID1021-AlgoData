import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SelectionSort selection = new SelectionSort();
        int[] array = createRandomArray(20);
        array = selection.selectionShort(array);
        for (int i : array) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
/**
 * creates an array of specified size with random numbers. Can contain duplicates.
 * @param arraySize the desired size
 * @return an unsorted array.
 */
    private static int[] createRandomArray(int arraySize){
        Random rnd = new Random();
        int[] rndArray = new int[arraySize];
        for (int i = 0; i < rndArray.length; i++) {
            rndArray[i] = rnd.nextInt(arraySize);
        }
        return rndArray;
    }


}
