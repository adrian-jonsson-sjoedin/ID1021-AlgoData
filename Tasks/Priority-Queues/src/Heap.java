public class Heap<P extends Comparable, T> {
    Node root;

    public class Node {
        public P priority;
        public T value;
        public int size;
        public Node left;
        public Node right;

        private Node(P priority, T value, int size) {
            this.priority = priority;
            this.value = value;
            this.size = size;
            this.left = this.right = null;
        }

        private void add(P priority, T item) {
            if (this.priority == priority) {
                System.out.println("There already is an item with this priority in the 
                queue. Please choose another priority for this");
            }
            if (this.key > k) {
                if (this.left == null) {
                    this.left = new Node(k, v);
                } else {
                    this.left.add(k, v);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(k, v);
                } else {
                    this.right.add(k, v);
                }
            }
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
        if (root == null) { // if tree is empty we add the first item as root and it will have a size of 1
            root = new Node(priority, item, 1);
        } else {
            root.add(priority, item);
        }
    }

    public Integer lookup(Integer key) {
        return root.lookup(key);
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
