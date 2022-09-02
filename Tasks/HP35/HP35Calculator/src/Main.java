import java.util.Random;

public class Main {

    public static void main(String[] args) {
//        Calculator calc = new Calculator(new Item[]{new Item(3), new Item(4), new Item(5),
//        new Item(ItemType.MUL), new Item(ItemType.SUB)});
//        System.out.println(calc.runStatic());

//        DynamicCalculator dynamicCalculator = new DynamicCalculator(new Item[]{new Item(3), new Item(4), new Item(5),
//                new Item(ItemType.MUL), new Item(ItemType.SUB)});
//        System.out.println(dynamicCalculator.run());
//        dynamicCalculator.stack.display();

//        dynamicStackTest();

        /*Benchmarking*/

        benchmarkStaticCalc(1_000);
        benchmarkDynamicCalc(1_000);
        benchmarkStaticCalc(10_000);
        benchmarkDynamicCalc(10_000);
        benchmarkStaticCalc(1_000_000);
        benchmarkDynamicCalc(1_000_000);

        DynamicCalculator socialCalc = new DynamicCalculator(new Item[]{new Item(10), new Item(9), new Item(2), new Item(ItemType.MUL),
                new Item(ItemType.MULTOADD), new Item(4), new Item(4), new Item(7), new Item(2),
                new Item(5), new Item(ItemType.MUL), new Item(ItemType.MULTOADD), new Item(1),
                new Item(2), new Item(7), new Item(ItemType.MUL), new Item(ItemType.MULTOADD), new Item(ItemType.ADD),
                new Item(ItemType.ADD), new Item(ItemType.ADD), new Item(ItemType.ADD), new Item(ItemType.ADD), new Item(ItemType.ADD),
                new Item(ItemType.MOD), new Item(ItemType.SUB)});
        System.out.println(socialCalc.run());
    }

    //generates a random expression that will fill up the stack to the fourth position
    public static Item[] generateRandomExpression() {
        Random rnd = new Random();
        Item[] expression = new Item[1000];
        for (int i = 0; i <= 500; i++) {
            expression[i] = new Item(rnd.nextInt(-10_000, 10_000));
        }
        for (int j = 501; j < 1000; j++) {
            expression[j] = new Item(ItemType.ADD);
        }
        return expression;
    }

    public static void benchmarkStaticCalc(int loops) {
        long startTime;
        long endTime;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < loops; i++) {
            Calculator cal = new Calculator(generateRandomExpression());
            startTime = System.nanoTime();
            cal.runStatic();
            endTime = System.nanoTime();
            if ((double) (endTime - startTime) < min) {
                min = (double) (endTime - startTime);
            }
        }
        //  double averageTimePerCalculation = sum / loops;
        System.out.println("Static stack: " + min + "ns");
    }

    public static void benchmarkDynamicCalc(int loops) {
        long startTime;
        long endTime;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < loops; i++) {
            DynamicCalculator cal = new DynamicCalculator(generateRandomExpression());
            startTime = System.nanoTime();
            cal.run();
            endTime = System.nanoTime();
            if (((double) (endTime - startTime)) < min) {
                min = (double) (endTime - startTime);
            }
        }
        System.out.println("Dynamic stack: " + min + "ns");
    }

    public static void stackTest() {
        Stack stack = new Stack();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        while (!stack.isEmpty()) {
            System.out.printf(" %d", stack.pop());
        }
    }

    public static void dynamicStackTest() {
        DynamicStack stack = new DynamicStack(2);
        stack.push(4);
        stack.push(2);
        stack.display();
        stack.push(4);
        stack.push(2);
        stack.display();
        stack.push(4);
        stack.push(2);
        stack.display();
        stack.pop();
        stack.pop();
        stack.pop();
//        stack.pop();
//        stack.pop();
//        stack.pop();
        stack.display();
    }
}
