//O(n) to add. Constant time to remove. 
public class QueueList {
    Node first;
    // Node last;

    private class Node {
        private Integer item;
        private Node next;

        public Node(Integer item, Node nxt) {
            this.item = item;
            this.next = nxt;
        }
    }

    public QueueList() {
        this.first = null;
        // this.last = null;
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

    // O(n) to add
    public void add(Integer item) {
        if (this.first == null) { // add node first if queue is empty
            this.first = new Node(item, null);
            return;
        }
        // if head of queue has lower priority than item
        Node current = this.first;
        Node after;
        if (item < current.item) {
            Node newNode = new Node(item, current);
            this.first = newNode;
        } else {
            while (current.next != null && current.next.item < item) {
                current = current.next;
            }
            after = current.next;
            current.next = new Node(item, after);
        }

    }

    // removes the first item in list which is the item with highest priority
    public Integer remove() {
        if (this.first == null) {
            System.out.println("Queue is empty. Nothing to retrieve");
            return null;
        }
        int retrievedItem = this.first.item;
        this.first = this.first.next;
        return retrievedItem;
    }

    public static void main(String[] args) {
        QueueList queue = new QueueList();
        queue.add(23);
        queue.add(89);
        queue.add(69);
        queue.add(66);
        queue.print();
        queue.add(13);
        queue.print();
        System.out.println("removed: " + queue.remove());
        queue.print();
        System.out.println("removed: " + queue.remove());
        queue.print();
        System.out.println("removed: " + queue.remove());
        queue.print();
        System.out.println("removed: " + queue.remove());
        queue.print();
        System.out.println("removed: " + queue.remove());
        queue.print();
        System.out.println("removed: " + queue.remove());
        queue.add(23);
        queue.print();

    }
}