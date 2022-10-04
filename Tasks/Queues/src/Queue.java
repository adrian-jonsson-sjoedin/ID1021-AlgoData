public class Queue<Item> {
    Node first;
    Node last;

    private class Node {
        private Item item;
        private Node next;

        public Node(Item item, Node nxt) {
            this.item = item;
            this.next = nxt;
        }
    }

    public Queue() {
        this.first = null;
        this.last = null;
    }

    public void print() {
        if (this.first == null) {
            System.out.println("print(): queue is empty");
            return;
        }
        Node current = this.first;
        System.out.println("start of queue");
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void enqueue(Item item) {
        Node newNode = new Node(item, null);
        if (this.first == null)
            this.first = newNode;
        if (this.last != null)
            this.last.next = newNode;
        this.last = newNode;
    }

    public Item dequeue() {
        if (this.first == null) {
            System.out.println("dequeue(): queue is empty");
            return null;
        }
        Item returnedItem = this.first.item;
        Node retrievedNode = this.first;
        this.first = this.first.next;
        if (retrievedNode == this.last)
            this.last = null;
        return returnedItem;
    }

    public static void main(String[] args) {
        Queue queue = new Queue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        queue.enqueue(6);
        System.out.println(queue.dequeue());
        queue.print();
    }
}
