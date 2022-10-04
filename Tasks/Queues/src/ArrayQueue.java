public class ArrayQueue<Item> {
    private Item[] queue;
    private int first;
    private int last;
    private int size;
    private int itemsInQueue = 0;

    public ArrayQueue(int size) {
        this.size = size;
        this.queue = (Item[]) new Object[this.size];
        this.first = this.last = 0;
    }

    public boolean isEmpty() {
        return this.itemsInQueue == 0;
    }

    public void enqueue(Item item) {

    }

}
