public class ArrayQueue<Item> {
    private Item[] queue;
    private int first = 0;
    private int last = 0;
    private int size;
    private int itemsInQueue = 0;

    public ArrayQueue(int size) {
        this.size = size;
        this.queue = (Item[]) new Object[this.size];
    }

    public boolean isEmpty() {
        return this.itemsInQueue == 0;
    }

    private boolean isFull() {
        return this.itemsInQueue == this.size;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            this.queue[this.first] = item;
            this.itemsInQueue++;
            this.last++;
            return;
        }
        if (!isEmpty()) {
            if ((this.last - 1) == (this.size - 1) && !isFull()) {
                this.last = 0;
            }
            if (isFull()) {
                resize();

            }
            if (this.last == 0) {
                this.queue[this.last] = item;
                this.last++;
                this.itemsInQueue++;
            } else {
                this.queue[this.last] = item;
                this.last++;
                this.itemsInQueue++;
            }
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            System.out.println("dequeue(): queue is empty");
            return null;
        }
        Item returnedItem = this.queue[this.first];
        this.queue[this.first] = null;
        this.first++;
        this.itemsInQueue--;

        // check if first has passed last. if it has it means all items are dequeued
        // we can then start filling it up from the start again.

        // if (this.first > this.last && this.queue[0] == null)
        // this.first = this.last = 0;
        return returnedItem;
    }

    public void print() {
        System.out.println("Start of array");
        for (int i = 0; i < this.size; i++) {
            System.out.println(queue[i]);
        }
    }

    public void resize() {
        int newSize = this.size * 2;
        Item[] newQueue = (Item[]) new Object[newSize];
        int j = 0;
        for (int i = this.first; i < this.size; i++) {
            newQueue[j] = this.queue[i];
            j++;
        }
        for (int i = 0; i < this.first; i++) {
            newQueue[j] = this.queue[i];
            j++;
        }
        this.queue = newQueue;
        this.size = newSize;
        this.first = 0;
        this.last = j;
        newQueue = null;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue<Integer>(4);
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.print();
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.print();
        System.out.println("retrieved item: " + queue.dequeue());
        System.out.println("retrieved item: " + queue.dequeue());
        System.out.println("retrieved item: " + queue.dequeue());
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.print();
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);
        queue.enqueue(18);
        queue.print();
        System.out.println("retrieved item: " + queue.dequeue());
        System.out.println("retrieved item: " + queue.dequeue());
        System.out.println("retrieved item: " + queue.dequeue());
        queue.enqueue(19);
        queue.enqueue(20);

        queue.print();
        // queue.enqueue(0);
        // queue.enqueue(1);
        // queue.enqueue(2);
        // queue.enqueue(3);
        // queue.enqueue(4);
        // queue.print();
        // System.out.println("retrieved item: " + queue.dequeue());
        // System.out.println("retrieved item: " + queue.dequeue());
        // System.out.println("retrieved item: " + queue.dequeue());
        // System.out.println("retrieved item: " + queue.dequeue());
        // queue.print();
        // System.out.println("retrieved item: " + queue.dequeue());
    }
}
