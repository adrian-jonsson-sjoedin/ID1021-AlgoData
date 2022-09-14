public class Main {
    public static void main(String[] args) {
        SelectionSort selection = new SelectionSort();
        int[] array = new int[]{4,6,1,10,9,3,7,2};
        array = selection.selectionShort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    private static int[] create randomArray(int arraySize){
        
    }


}
