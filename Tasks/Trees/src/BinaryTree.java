import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class BinaryTree implements Iterable<Integer> {
    Node root;

    public class Node {
        public Integer key;
        public Integer value;
        public Node left;
        public Node right;

        private Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }

        private void add(Integer k, Integer v) {
            if (this.key == k) {
                this.value = v;
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

        private Integer lookup(Integer k) {
            Node current = this;
            while (current != null) {
                if (current.key == k) {
                    return current.value;
                } else if (current.key > k) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            return null;
        }

        public void print() {
            if (left != null)
                left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if (right != null)
                right.print();
        }
    }

    public BinaryTree() {
        root = null;
    }

    /*
     * Creates a binary tree of specified size and populates it with key values
     */
    public BinaryTree(int size) {
        Random rnd = new Random();
        // root = new Node((size * 2) + rnd.nextInt(size / 2) - rnd.nextInt(size / 2),
        // rnd.nextInt(size * 10));
        // for (int i = 0; i < size; i++) {
        // add(rnd.nextInt(size * 4) + 1, rnd.nextInt(size * 10));
        // }
        root = new Node(size / 2, size / 2);
        for (int i = 0; i < size; i++) {
            add(rnd.nextInt(size), rnd.nextInt(size));
        }
    }

    public void add(Integer k, Integer v) {
        if (root == null) {
            root = new Node(k, v);
        } else {
            root.add(k, v);
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

    public static void benchmarkLookup(int treeSize, int runs) {
        Random rnd = new Random();
        long tMin = Long.MAX_VALUE;
        long time = 0;
        BinaryTree bTree = new BinaryTree(treeSize);

        int tempKey = rnd.nextInt(treeSize);
        bTree.lookup(tempKey);

        for (int i = 0; i < runs; i++) {
            int key = rnd.nextInt(treeSize);
            // System.out.println("key " + key);
            long timeStart = System.nanoTime();
            bTree.lookup(key);
            long timeStop = System.nanoTime();
            time += timeStop - timeStart;
            if ((timeStop - timeStart) < tMin) {
                tMin = timeStop - timeStart;
            }
        }
        System.out.printf("%d\t %d\t %d%n", treeSize, tMin, time / runs);
        // System.out.println("Min: " + tMin);
        // System.out.println("Average time: " + time / runs);

    }

    public static void main(String[] args) {
        // for (int size = 1_000_000; size <= 100_000_000; size = 2 * size) {

        // benchmarkLookup(size, 100);
        // }
        BinaryTree tree = new BinaryTree();
        tree.add(5, 105);
        tree.add(2, 102);
        tree.add(7, 107);
        tree.add(1, 101);
        tree.add(8, 108);
        tree.add(6, 106);
        tree.add(3, 103);

        // tree.print();

        for (int i : tree)
            System.out.println("Next value " + i);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new TreeIterator(root);
    }

    public class TreeIterator implements Iterator<Integer> {
        private Node next;
        private Stack<Node> stack;

        public TreeIterator(Node current) {
            stack = new Stack<Node>();
            moveLeft(current);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();

        }

        // we start all the way at the bottom left with the pointer pointing at null.
        // We then pop the stack and set that node to curr, and check if curr has
        // branches down to the right.
        // If it does we push that to the stack and all the branches down left from it.
        // we then return the value of curr. This way next time we call this method the
        // top node on the stack will be the node furthest down on the left of the node
        // on the right of curr and should thus be the next smallest key in the tree
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node curr = stack.pop();
            if (curr.right != null) {
                moveLeft(curr.right);
            }
            next = curr;
            return curr.value;
        }

        // we want to move all the way down left first so as long as current
        // isn't null we push the current node to the stack and then move
        // down left till we reach the null pointer
        private void moveLeft(Node current) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
