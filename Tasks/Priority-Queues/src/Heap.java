public class Heap<P extends Comparable, T> {
    Node root;

    private class Node {
        private P priority;
        private T value;
        private int size;
        private Node left;
        private Node right;

        public Node(P priority, T value, int size) {
            this.priority = priority;
            this.value = value;
            this.size = size;
            this.left = this.right = null;
        }

        public void print() {
            if (left != null)
                left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if (right != null)
                right.print();
        }
    }

    public Heap() {
        root = null;
    }

    public void add(P priority, T item) {
        int temp;
        if (root == null) { // if tree is empty we add the first item as root and it will have a size of 1
            root = new Node(priority, item, 1);
            return;
        }
        root.size++; // increment the size of the first node, i.e. the root by one
        Node current = this.root;
        if (priority.compareTo(root.priority) < 0) { // priority.compareTo(root.priority) = -1 if priority <
                                                     // root.priority
            // new item should be placed as root
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

        BinaryTree tree = new BinaryTree();
        tree.add(20, 20);
        tree.add(8, 8);
        tree.add(22, 22);
        tree.add(4, 4);
        tree.add(12, 12);
        tree.add(10, 10);
        tree.add(14, 14);

        // tree.print();

    }
}
