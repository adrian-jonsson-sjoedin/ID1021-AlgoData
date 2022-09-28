import java.util.EmptyStackException;

/*
 * Genseric dynamic array stack
 */
public class Stack<Item> {
    private Item[] stack;
    private int stackPointer;

    public Stack() {
        this.stack = (Item[]) new Object[1];
        this.stackPointer = 0;
    }

    public void push(Item item) {
        if (this.stackPointer == size())
            resize(2 * size());
        this.stack[this.stackPointer++] = item;
    }

    public Item pop() {
        Item poppedItem = this.stack[--this.stackPointer];
        this.stack[this.stackPointer] = null;
        if (this.stackPointer > 0 && this.stackPointer == size() / 4)
            resize(size() / 2);
        return poppedItem;
    }

    public boolean isEmpty() {
        return stackPointer == 0;
    }

    private void resize(int amount) {
        Item[] newStack = (Item[]) new Object[amount];
        for (int i = 0; i < this.stackPointer; i++) {
            newStack[i] = this.stack[i];
        }
        this.stack = newStack;
        newStack = null;
    }

    public int size() {
        return (this.stack.length);
    }
}