public class Sort {

    public Sort() {
    }

    /**
     * selection sort algorithm. Has time complexity O(n^2)
     * 
     * @param array the array to sort
     * @return the same array but sorted
     */
    public static void selection(int[] array) {
        int minIndex;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            // find minimum element in an unsorted array
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // swap the found minimum element with the element we started at, i.e. element
            // at index i
            temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    /**
     * Insertion sort algorithm. Average and worst case time complexity of O(n^2).
     * Best case of O(n).
     * This algorithm is good if the array is already partly sorted.
     * 
     * @param array
     */
    public static void insertion(int[] array) {
        int min;
        int j;
        for (int i = 1; i < array.length; i++) {
            min = array[i];
            j = i - 1;
            // Compare key with each element on the left of it until an element smaller than
            // it is found.
            while (j >= 0 && min < array[j]) {
                array[j + 1] = array[j]; // move elements to the right in the array
                j--;
            }
            array[j + 1] = min;
        }
    }
}
