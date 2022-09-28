public class Main {
    public static void main(String[] args) {
        int treeSize = 1000;
        BinaryTree bTree1 = new BinaryTree();
        BinaryTree bTree = new BinaryTree(treeSize);
        bTree1.add(12, 112);
        bTree1.add(6, 106);
        bTree1.add(14, 114);
        bTree1.add(8, 108);
        bTree1.add(2, 102);
        bTree1.print();
        System.out.println(bTree.lookup(100));
        System.out.println(bTree1.lookup(8));

        // bTree.benchmarkLookup(1000000, 100);
        // for (int size = 1_000; size <= 10_000; size += 1_000) {
        // bTree.benchmarkLookup(size, 10000);
        // }
        // bTree.benchmarkLookup(100, 100);
        // bTree.benchmarkLookup(1000, 100);
        // bTree.benchmarkLookup(10_000, 100);
        // bTree.benchmarkLookup(100_000, 100);
        // bTree.benchmarkLookup(1_000_000, 100);
        // bTree.benchmarkLookup(100_000_000, 100);

    }
}