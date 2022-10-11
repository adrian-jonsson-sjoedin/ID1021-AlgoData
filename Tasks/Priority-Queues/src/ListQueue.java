public class ListQueue {
    Node first;
    Node last;

    private class Node {
        private Integer item;
        private Node next;

        public Node(Integer item, Node nxt) {
            this.item = item;
            this.next = nxt;
        }
    }

    public ListQueue() {
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

    public void add(Integer item) {
        Node newNode = new Node(item, null);
        if (this.first == null)
            this.first = newNode;
        if (this.last != null)
            this.last.next = newNode;
        this.last = newNode;
    }

    public Integer remove() {
        if (this.first == null) {
            System.out.println("remove(): queue is empty");
            return null;
        }
        Node current = this.first;
        int min = Integer.MAX_VALUE;
        Node before = null;
        Node after = null;
        while (current != this.last) {
            if (current.item > current.next.item && current.next.item < min) {
                min = current.next.item;
                before = current;// save the node before min
                after = current.next.next; // save the node after min
            }
            current = current.next;
        }
        before.next = after;
        return min;
    }

    public static void main(String[] args) {
        ListQueue queue = new ListQueue();
        queue.add(5);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.print();
        System.out.println("removed: " + queue.remove());
        queue.print();
    }
}