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

    /**
     * Merge sort algorithm using recursive callback. Time complexity of O(n*log(n))
     * 
     * @param original the array we want sorted
     */
    public static void sort(int[] original) {
        if (original.length == 0) {
            return;
        }
        int[] auxillary = new int[original.length];
        sort(original, auxillary, 0, original.length - 1);
    }

    private static void sort(int[] original, int[] auxillary, int low, int high) {
        if (low != high) {
            int middle = low + (high - low) / 2;
            sort(original, auxillary, low, middle);
            sort(original, auxillary, middle + 1, high);
            merge(original, auxillary, low, middle, high);
        }
    }

    private static void merge(int[] original, int[] auxillary, int low, int middle, int high) {
        // copy all items from lo to hi from org to aux
        for (int i = low; i <= high; i++) {
            auxillary[i] = original[i];
        }
        // let's do the merging
        int i = low; // the index in the first part
        int j = middle + 1; // the index in the second part
        // for all indices from low to high
        for (int k = low; k <= high; k++) {
            // corner case
            // if i (low) is greater than mid, move the j item to the org array, update j
            if (i > middle) {
                original[k] = auxillary[j++];

                // corner case
                // else if j is greater than hi, move the i item to the org array, update i
            } else if (j > high) {
                original[k] = auxillary[i++];

                // else if the i item is smaller than the j item,
                // move it to the org array, update i
            } else if (auxillary[i] < auxillary[j]) {
                original[k] = auxillary[i++];

                // else you can move the j item to the org array, update j
            } else {
                original[k] = auxillary[j++];
            }
        }
    }
}
