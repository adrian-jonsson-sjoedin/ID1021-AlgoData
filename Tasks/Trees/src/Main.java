public class Main {
    public static void main(String[] args) {
        BinaryTree bTree = new BinaryTree();
        bTree.add(12, 112);
        bTree.add(6, 106);
        bTree.add(14, 114);
        bTree.add(8, 108);
        bTree.add(2, 102);
        bTree.print();
        System.out.println(bTree.lookup(1));
    }
}