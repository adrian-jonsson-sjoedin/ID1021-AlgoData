import java.util.Random;

/**
 * A simple linked list class. Last in first out.
 */
public class LinkedList {
    private int size;
    private Node head;

    /**
     * Helper class that is needed to implement the link structure
     */
    private class Node {
        private int value; // the value for the item we add
        private Node next; // pointer to next element in the list

        /**
         * Constructor for the Node class
         * 
         * @param value the value for the node
         * @param node  pointer to the next node
         */
        public Node(int value, Node node) {
            this.value = value;
            this.next = node;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     * Constructor that instantiates an empty list
     */
    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Checks if list is empty
     * 
     * @return true if empty. Otherwise false
     */
    public boolean isEmpty() {
        return (getSize() == 0);
    }

    /**
     * Method that is used to add new int element to the list
     * 
     * @param value of int to add to list
     */
    public void add(int value) {
        Node newHead = new Node(value, this.head);
        this.head = newHead;
        this.size++;
    }

    /**
     * Removes the last added item to the list and returns it
     * 
     * @return the node that vas last added
     */
    public Node remove() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        Node poppedNode = this.head;
        this.head = head.next;
        this.size--;
        return poppedNode;
    }

    /**
     * Method that prints the current stack to the terminal
     */
    public void display() {
        if (isEmpty())
            System.out.println("The list is empty!");
        else {
            System.out.println(head.getValue() + " <- Top of the stack");
            Node current = head.next;
            while (current != null) {
                System.out.println(current.getValue());
                current = current.next;
            }
        }
    }

    public void appendEnd(LinkedList linkedList) {
        Node current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = linkedList.head;
    }

    public void appendFirst(LinkedList linkedList) {
        Node current = linkedList.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = this.head;
        this.head = linkedList.head;
    }

    public double benchmarkAdd(int nrOfAddOperations, int iterations) {
        Random rand = new Random();
        double min = Double.POSITIVE_INFINITY;
        double time;
        for (int i = 0; i < iterations; i++) {
            LinkedList list = new LinkedList();
            for (int j = 0; j < nrOfAddOperations; j++) {
                double timeStart = System.nanoTime();
                list.add(rand.nextInt(nrOfAddOperations));
                time = System.nanoTime() - timeStart;
                if (time < min) {
                    min = time;
                }
            }
        }
        return min;
    }

    public double benchmarkAppendEnd(int aSize, int iterations) {
        double min = Double.POSITIVE_INFINITY;
        double timeStart;
        double time;
        for (int m = 0; m < iterations; m++) {
            LinkedList b = new LinkedList();
            LinkedList a = new LinkedList();
            for (int i = 1; i <= 20; i++) {// create fixed linkedlist b
                b.add(i);
            }
            // create linkedlist a with increasing size
            for (int j = 21; j <= aSize + 21; j++) {
                a.add(j);
            }
            timeStart = System.nanoTime();
            b.appendEnd(a);
            time = System.nanoTime() - timeStart;
            if (time < min) {
                min = time;
            }
        }
        return min;
    }

    public double benchmarkAppendFirst(int aSize, int iterations) {
        double min = Double.POSITIVE_INFINITY;
        double timeStart;
        double time;
        for (int m = 0; m < iterations; m++) {
            LinkedList b = new LinkedList();
            LinkedList a = new LinkedList();
            for (int i = 1; i <= 20; i++) {// create fixed linkedlist b
                b.add(i);
            }
            // create linkedlist a with increasing size
            for (int j = 21; j <= aSize + 21; j++) {
                a.add(j);
            }
            timeStart = System.nanoTime();
            b.appendFirst(a);
            time = System.nanoTime() - timeStart;
            if (time < min) {
                min = time;
            }
        }
        return min;
    }

    /*
     * Main method for class for testing that the add, remove and append method
     * works as intended.
     */
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        LinkedList list2 = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.display();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list.appendEnd(list2);
        list.display();
    }
}
