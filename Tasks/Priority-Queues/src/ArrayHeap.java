import java.util.NoSuchElementException;

/*
 * Non resizable min heap using an array
 */
public class ArrayHeap {
    private final int[] priorityQueue; // store items at indices 1 to n
    private int itemsInQueue; // number of items on priority queue
    private final int maxSize;

    public ArrayHeap(int maxSize) {
        this.maxSize = maxSize;
        this.itemsInQueue = 0;
        this.priorityQueue = new int[this.maxSize];
    }

    private int getLeftChildIndex(int parentIndex) {
        return (2 * parentIndex + 1);
    }

    private int getRightChildIndex(int parentIndex) {
        return (2 * parentIndex + 2);
    }

    private int getParentIdex(int childIndex) {
        return ((childIndex - 1) / 2);
    }

    private boolean hasLeftChild(int index) {
        return (getLeftChildIndex(index) < itemsInQueue);
    }

    private boolean hasRightChild(int index) {
        return (getRightChildIndex(index) < itemsInQueue);
    }

    private boolean hasParent(int index) {
        return (getParentIdex(index) >= 0);
    }

    private int leftChildValue(int parentIndex) {
        return this.priorityQueue[getLeftChildIndex(parentIndex)];
    }

    private int rightChildValue(int parentIndex) {
        return this.priorityQueue[getRightChildIndex(parentIndex)];
    }

    private int parentValue(int childIndex) {
        return this.priorityQueue[getParentIdex(childIndex)];
    }

    private void swapElements(int index1, int index2) {
        int temp = this.priorityQueue[index1];
        this.priorityQueue[index1] = this.priorityQueue[index2];
        this.priorityQueue[index2] = temp;
    }

    private void bubble() {
        int index = this.itemsInQueue - 1; // index to last element in heap
        while (hasParent(index) && parentValue(index) > priorityQueue[index]) {
            swapElements(getParentIdex(index), index);
            index = getParentIdex(index);
        }
    }

    private void sink() {
        int index = 0; // where we had the old root
        while (hasLeftChild(index)) {
            int smallestChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChildValue(index) < leftChildValue(index)) {
                smallestChildIndex = getRightChildIndex(index);
            }
            if (this.priorityQueue[index] < priorityQueue[smallestChildIndex]) {
                break;
            } else {
                swapElements(index, smallestChildIndex);
            }
            index = smallestChildIndex;
        }
    }

    /**
     * Returns the minimum element of the priority queue
     * 
     * @return first element of the array which is the element with lowest value
     */
    public int peek() {
        if (this.itemsInQueue == 0) {
            System.out.println("Heap is empty");
            throw new NoSuchElementException();
        }
        return this.priorityQueue[0];
    }

    public int remove() {
        if (this.itemsInQueue == 0) {
            System.out.println("Heap is empty");
            throw new NoSuchElementException();
        }
        int returnElement = this.priorityQueue[0];
        this.priorityQueue[0] = this.priorityQueue[this.itemsInQueue - 1]; // set last element as root
        this.itemsInQueue--;
        sink(); // let the new root sink down
        return returnElement;
    }

    public void add(int item) {
        if (this.itemsInQueue == maxSize) {
            System.out.println("Heap is full. Can't add item");
            return;
        } // items in queue will always be a value that gives an index where there is
          // nothing inserted yet
        this.priorityQueue[this.itemsInQueue] = item;
        this.itemsInQueue++;
        bubble();
    }

    public void printHeap() {
        System.out.println("The Min Heap is ");
        for (int i = 0; i < this.itemsInQueue / 2; i++) {
            System.out.println("PARENT : " + this.priorityQueue[i]);

            System.out.println("--LEFT CHILD : " + this.priorityQueue[2 * i + 1]);

            System.out.println("--RIGHT CHILD : " + this.priorityQueue[2 * i + 2]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayHeap heap = new ArrayHeap(10);
        heap.add(10);
        heap.add(2);
        heap.add(7);
        heap.add(15);
        heap.add(90);
        heap.add(19);
        heap.add(8);
        heap.add(22);
        heap.add(9);
        heap.printHeap();
        heap.remove();
        heap.remove();
        System.out.println("-------------------");
        heap.printHeap();
    }
}
