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

    public void append(LinkedList linkedList) {
        Node current = this.head;
        while(current.next!=null){
            current = current.next;
        }
        current.next = linkedList.head;
    }
}
