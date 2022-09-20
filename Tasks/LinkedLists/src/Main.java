public class Main {

    public static void main(String[] args) {
        int iterations = 10_000;
        LinkedList linkedList = new LinkedList();
        ArrayAppend arAppend = new ArrayAppend();

        // System.out.println("Add operation benchmark");
        // linkedList.benchmarkAdd(100, iterations);
        // for (int i = 0; i < 100; i++) {
        // System.out.println(linkedList.benchmarkAdd(1000, iterations));
        // }

        // System.out.println("Append list a to end of fixed list b");
        // int arraySize = 1000;
        // for (int i = 10; i <= arraySize; i += 10) {
        // System.out.printf("%d\t %f%n", i, linkedList.benchmarkAppendEnd(i,
        // iterations));
        // }

        // System.out.println("Append list a to head of fixed list b");
        // int arraySize = 1000;
        // for (int i = 10; i <= arraySize; i += 10) {
        // System.out.printf("%d\t %f%n", i, linkedList.benchmarkAppendFirst(i,
        // iterations));
        // }

        // System.out.println("Append increasing size array to end of of fixed size
        // array");
        // int arraySize = 10000;

        // for (int i = 100; i <= arraySize; i += 100) {
        // System.out.printf("%d\t %f%n", i, arAppend.benchmarkArrayAppendEnd(i,
        // iterations));
        // }
        System.out.println("Append increasing size array infront of of fixed size array");
        int arraySize = 10000;

        for (int i = 100; i <= arraySize; i += 100) {
            System.out.printf("%d\t %f%n", i, arAppend.benchmarkArrayAppendEnd(i,
                    iterations));
        }
    }

}