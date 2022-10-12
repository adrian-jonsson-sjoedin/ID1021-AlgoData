import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.plaf.ColorUIResource;
import javax.swing.text.html.HTMLDocument.RunElement;

public class Heap<P extends Comparable, T> {
    Node root;

    private class Node {
        private P priority;
        private T item;
        private int size;
        private Node left;
        private Node right;

        public Node(P priority, T value, int size) {
            this.priority = priority;
            this.item = value;
            this.size = size;
            this.left = this.right = null;
        }

        // depth first traversal
        public void print() {
            if (left != null)
                left.print();
            System.out.println(" key: " + priority + "\tvalue: " + item);
            if (right != null)
                right.print();
        }

        private void add(P prio, T item) {
            // we want to move the item and prio of the current node down if the item we
            // want to add has lower priority than the node we're in
            if (prio.compareTo(this.priority) < 0) {
                P tempPriority = this.priority;
                T tempItem = this.item;
                this.priority = prio;
                this.item = item;
                prio = tempPriority;
                item = tempItem;
            }
            this.size++;
            if (this.left == null) {
                this.left = new Node(prio, item, 1);
            } else if (this.right == null) {
                this.right = new Node(prio, item, 1);
            } else if (this.right.size < this.left.size) {
                this.right.add(prio, item);
            } else {
                this.left.add(prio, item);
            }

        }

        private Node remove(Node root) {
            // if the left branch is empty we promote the right branch to root.
            if (this.left == null) {
                root = this.right;
                return root;
            }
            // if the right branch is empty we promote the left branch to root
            if (this.right == null) {
                root = this.left;
                return root;
            }
            if (root.left.priority.compareTo(root.right.priority) < 0) {
                root.priority = root.left.priority;
                root.item = root.left.item;
                root.size--;
                if (root.left.size == 1) {
                    root.left = null;
                } else {
                    root.left = root.left.remove(root);
                }
                return root;
            } else {
                root.priority = root.right.priority;
                root.item = root.right.item;
                root.size--;
                if (root.right.size == 1) {
                    root.right = null;
                } else {
                    root.right = root.right.remove(root);
                }
            }
            return root;
        }
    }

    public Heap() {
        root = null;
    }

    public void breathFirstPrint() {
        Queue<Node> queue = new Queue<Node>();
        Node current = root;
        if (current == null) {
            System.out.println("Heap is empty");
            return;
        }
        System.out.println(" key: " + current.priority + "\tvalue: " + current.item);
        if (current.left != null)
            System.out.println("\t left: " + current.left.priority);
        if (current.right != null)
            System.out.println("\t right: " + current.right.priority);
        while (current != null) {
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
            current = queue.dequeue();
            if (current == null)
                return;
            System.out.println(" key: " + current.priority + "\tvalue: " + current.item);
            if (current.left != null)
                System.out.println("\t left: " + current.left.priority);
            if (current.right != null)
                System.out.println("\t right: " + current.right.priority);
        }
    }

    public void add(P priority, T item) {
        if (root == null) {
            root = new Node(priority, item, 1);
        } else {
            root.add(priority, item);
        }
    }

    public int remove() {
        if (root == null) {
            System.out.println("Heap is empty");
            return -1;
        } else {
            int oldRootToBeReturned = (int) this.root.priority;
            this.root = root.remove(root);
            return oldRootToBeReturned;
        }
    }

    public void print() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        root.print();
    }

    public static void main(String[] args) {
        Heap tree = new Heap();
        tree.add(20, 20);
        tree.add(8, 8);
        tree.add(22, 22);
        // tree.add(4, 4);
        // tree.add(12, 12);
        // tree.add(10, 10);
        // tree.add(14, 14);

        tree.add(4, 4);
        tree.breathFirstPrint();
        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();

        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();

        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();
        System.out.println("remove " + tree.remove());
        // tree.remove();
        tree.breathFirstPrint();

    }

}
