public class SelectionSort {


    
    public int[] selectionShort(int[] array){
        int minIndex;
        int temp;
        for(int i = 0; i< array.length - 1; i++){
            minIndex = i;
            for(int j = i+1; j< array.length; j++){
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            //swap the found minimum element with the element we started at, i.e. element at index i
            temp = array[minIndex];
            array[minIndex]= array[i];
            array[i] = temp;
        }
        return array;
    }
   
}
