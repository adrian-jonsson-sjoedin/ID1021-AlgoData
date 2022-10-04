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
        boolean full = false;

        return (this.last == this.first && this.queue[0] != null);
    }

    public void enqueue(Item item) {
        if (this.itemsInQueue == this.queue.length) {
            System.out.println("queue full. resize or wrap around");
        }

        if (!isEmpty() && this.last != (this.queue.length - 1)
                && this.queue[this.last] != null) {
            this.last++;
            this.queue[this.last] = item;
            this.itemsInQueue++;
        }
        if (this.last == this.queue.length - 1 && this.first != 0 && !isFull()) {
            this.last = 0;
            this.queue[this.last] = item;
            this.itemsInQueue++;

        }
        if (isEmpty()) {
            this.queue[this.first] = item;
            this.itemsInQueue++;
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            System.out.println("enqueue(): queue is empty");
            return null;
        }
        Item returnedItem = this.queue[this.first];
        this.queue[this.first] = null;
        this.first++;
        this.itemsInQueue--;
        // check if first has passed last. if it has it means all items are dequeued
        // we can then start filling it up from the start again.
        if (this.first > this.last && this.queue[0] == null)
            this.first = this.last = 0;
        return returnedItem;
    }

    public void print() {
        System.out.println("Start of array");
        for (int i = 0; i < this.queue.length; i++) {
            System.out.println(queue[i]);
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue<Integer>(3);
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.print();
        System.out.println("retrieved item: " + queue.dequeue());
        queue.print();
        queue.enqueue(3);
        queue.print();
        System.out.println("retrieved item: " + queue.dequeue());
        queue.print();
        System.out.println("retrieved item: " + queue.dequeue());
        queue.print();
        queue.enqueue(4);
        queue.print();
        queue.enqueue(5);
        queue.print();

    }

}
