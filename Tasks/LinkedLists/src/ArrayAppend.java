public class ArrayAppend {

    public int[] append(int[] array1, int[] array2) {
        int newArraySize = array1.length + array2.length;
        int[] newArray = new int[newArraySize];
        int index = 0;
        for (int i : array1) {
            newArray[index] = i;
            index++;
        }
        for (int i : array2) {
            newArray[index] = i;
            index++;
        }
        return newArray;
    }

    public double benchmarkArrayAppendEnd(int arraySize, int iterations) {
        double min = Double.POSITIVE_INFINITY;
        double timeStart;
        double time;
        for (int i = 0; i < iterations; i++) {
            int[] fixedArray = new int[] { 1, 2, 3, 4, 5, 6, 7,
                    8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
            int[] dynamicArray = new int[arraySize];
            // create the array that will increase in size as we run the append method
            for (int j : dynamicArray) {
                dynamicArray[j] = j + 21;
            }
            append(fixedArray, dynamicArray);
            timeStart = (double) System.nanoTime();
            append(fixedArray, dynamicArray);
            time = (double) (System.nanoTime() - timeStart);
            if (time < min) {
                min = time;
            }
        }
        return min;
    }

    public double benchmarkArrayAppendFirst(int arraySize, int iterations) {
        double min = Double.POSITIVE_INFINITY;
        double timeStart;
        double time;
        for (int i = 0; i < iterations; i++) {
            int[] fixedArray = new int[] { 1, 2, 3, 4, 5, 6, 7,
                    8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
            int[] dynamicArray = new int[arraySize];
            // create the array that will increase in size as we run the append method
            for (int j : dynamicArray) {
                dynamicArray[j] = j + 21;
            }
            append(dynamicArray, fixedArray);
            timeStart = (double) System.nanoTime();
            append(fixedArray, dynamicArray);
            time = (double) (System.nanoTime() - timeStart);
            if (time < min) {
                min = time;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ArrayAppend ap = new ArrayAppend();
        int[] a = new int[] { 1, 2, 3 };
        int[] b = new int[] { 4, 5, 6, 7 };
        int[] combined = ap.append(a, b);
        for (int i : combined) {
            System.out.println(i);
        }
    }
}
