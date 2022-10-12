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

        private Node remove() {
            // if the left branch is empty we promote the right branch to root.
            if (this.left == null) {
                this.priority = this.right.priority;
                this.item = this.right.item;
                this.right = null;
                this.size--;
                return this;
            }
            // if the right branch is empty we promote the left branch to root
            if (this.right == null) {
                this.priority = this.left.priority;
                this.item = this.left.item;
                this.left = null;
                this.size--;
                return this;
            }
            if (this.left.priority.compareTo(this.right.priority) < 0) {
                this.priority = this.left.priority;
                this.item = this.left.item;
                this.size--;
                if (this.left.size == 1) {
                    this.left = null;
                } else {
                    this.left = this.left.remove();
                }
                return this;
            } else {
                this.priority = this.right.priority;
                this.item = this.right.item;
                this.size--;
                if (this.right.size == 1) {
                    this.right = null;
                } else {
                    this.right = this.right.remove();
                }
            }
            return this;
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
        } else if (root.left == null && root.right == null) {
            int oldRootToBeReturned = (int) this.root.priority;
            root = null;
            return oldRootToBeReturned;
        } else {
            int oldRootToBeReturned = (int) this.root.priority;
            root.remove();
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
        tree.add(4, 4);
        tree.add(12, 12);
        tree.add(10, 10);
        tree.add(14, 14);

        tree.breathFirstPrint();
        System.out.println("--------------------------------------");

        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();
        System.out.println("--------------------------------------");

        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();
        System.out.println("--------------------------------------");

        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();
        System.out.println("--------------------------------------");

        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();
        System.out.println("--------------------------------------");

        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();
        System.out.println("--------------------------------------");

        System.out.println("remove " + tree.remove());
        tree.breathFirstPrint();
        System.out.println("--------------------------------------");

        System.out.println("last remove " + tree.remove());
        tree.breathFirstPrint();
        System.out.println("--------------------------------------");

    }

}
