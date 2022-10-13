import java.security.SecureRandom;

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

    // constant time to add
    public void add(Integer item) {
        Node newNode = new Node(item, null);
        if (this.first == null)
            this.first = newNode;
        if (this.last != null)
            this.last.next = newNode;
        this.last = newNode;
    }

    // removes the integer with lowest value. time complexity O(n)
    public Integer remove() {
        if (this.first == null) {
            System.out.println("remove(): queue is empty");
            return null;
        }
        if (this.first.next == null) {
            int min = this.first.item;
            this.first = null;
            return min;
        }
        Node current = this.first;
        int min = Integer.MAX_VALUE;
        Node beforeMin = null;
        boolean isFirst = false;
        Node prev = null;
        Node after = null;
        while (current != null && current.next != null) {
            if (current.item < min) {
                if (current == this.first) {
                    isFirst = true;
                    min = current.item;

                } else {
                    min = current.item;
                    beforeMin = prev;
                    after = current.next;
                    isFirst = false;
                }
            }
            prev = current;
            current = current.next;
        }
        if (isFirst) {
            this.first = this.first.next;
        } else {
            beforeMin.next = after;
        }
        return min;
    }

    private static int[] randomNumbers(int from, int to) {
        int n = to - from + 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        int[] result = new int[n];
        int x = n;
        SecureRandom rd = new SecureRandom();
        for (int i = 0; i < n; i++) {
            // k is a random index in [0,x]
            int k = rd.nextInt(x);
            result[i] = a[k];
            // we got a value from a[k]. we replace its value by the value from the
            // last index so that we will not get that value (a[k]) again
            a[k] = a[x - 1];
            x--;
        }
        return result;
    }

    public static void main(String[] args) {
        ListQueue queue = new ListQueue();
        int[] a = randomNumbers(0, 150);
        for (int i : a) {
            queue.add(i);
        }
        // queue.add(1);
        // queue.add(5);
        // queue.add(9);
        // queue.add(3);
        // queue.add(2);
        queue.print();
        for (int i = 0; i < 150; i++) {
            System.out.println("removed: " + queue.remove());
        }
        // while (!queue.isEmpty()) {
        // System.out.println("removed: " + queue.remove());
        // }
        // System.out.println("removed: " + queue.remove());
        // queue.print();
        // System.out.println("removed: " + queue.remove());
        // queue.print();
        // System.out.println("removed: " + queue.remove());
        // queue.print();
        // System.out.println("removed: " + queue.remove());
        // queue.print();
        // System.out.println("removed: " + queue.remove());
        // queue.print();
        // queue.add(7);
        // queue.add(0);
        // queue.print();
        // System.out.println("removed: " + queue.remove());
        // queue.print();
    }
}