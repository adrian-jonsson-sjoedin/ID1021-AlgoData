import java.util.EmptyStackException;

public class Stack {
    private int[] arr;
    private int index = 0;

    public Stack() {
        arr = new int[4];
    }

    public void push(int element) {

        if (isFull()) {
            throw new StackOverflowError("Stack is full");
        }

        arr[index] = element;
        index++;
    }

    public int pop() {

        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return arr[--index];
    }

    public boolean isEmpty() {
        if (index == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (index == 4) {
            return true;
        }
        return false;
    }


}
