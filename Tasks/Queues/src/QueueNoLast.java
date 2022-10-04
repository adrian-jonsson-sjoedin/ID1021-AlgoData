public class QueueNoLast {
    Node queue;

    private class Node {
        private Integer item;
        private Node next;

        public Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public QueueNoLast() {
        this.queue = null;
    }

    public void add(Integer item) {
        this.queue = new Node(item, this.queue);
    }

    public boolean isEmpty() {
        return this.queue == null;

    }

    public Integer retrieve() {
        Node prev = null;
        Node current = this.queue;
        if (isEmpty()) {
            System.out.println("queue is empty");
            return null;
        }
        while (current.next != null) {
            prev = current;
            current = current.next;
        }
        this.queue = prev;
        return current.item;
    }

    public void print() {
        Node current = this.queue;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
        System.out.println("start of queue");
    }

    public static void main(String[] args) {
        QueueNoLast queue = new QueueNoLast();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.print();
        System.out.println("retrieved item: " + queue.retrieve());
        queue.print();
    }
}
