public class BinaryTree {
    Node root;

    private class Node {
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

}
