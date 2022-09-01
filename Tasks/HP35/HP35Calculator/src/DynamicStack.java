import java.util.EmptyStackException;

public class DynamicStack {
    private int index;
    private int capacity;
    private int[] array;

    public DynamicStack(int cap) {
        capacity = cap;
        array = new int[capacity];
        index = -1;
    }

    public void push(int element) {
        if (isFull()) {
            expandArray();      //if array is full then increase its capacity
        }
        array[++index] = element;    //insert the data
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            reduceSize();                 //function to check if size can be reduced
            return array[index--];
        }
    }

    public boolean isFull() {
        if (capacity == index + 1)
            return true;
        else
            return false;
    }

    public boolean isEmpty() {
        if (index == -1)
            return true;
        else
            return false;
    }

    public void expandArray() {
        int curr_size = index + 1;
        int[] new_array = new int[curr_size * 2];
        for (int i = 0; i < curr_size; i++) {
            new_array[i] = array[i];
        }
        array = new_array;              //refer to the new array
        capacity = new_array.length;
    }

    public void reduceSize() {
        int curr_length = index + 1;
        if (curr_length < capacity / 2) {
            int[] new_array = new int[capacity / 2];
            System.arraycopy(array, 0, new_array, 0, new_array.length);
            array = new_array;
            capacity = new_array.length;
        }
    }

    public void display() {
        for (int i = 0; i <= index; i++) {
            System.out.print(array[i] + "=>");
        }
        System.out.println();
        System.out.println("ARRAY SIZE:" + array.length);
    }

}