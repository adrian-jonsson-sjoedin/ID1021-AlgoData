public class Main {

    public static void main(String[] args) {
//        Calculator calc = new Calculator(new Item[]{new Item(3), new Item(4), new Item(5),
//        new Item(ItemType.MUL), new Item(ItemType.SUB)});
//        System.out.println(calc.runStatic());

//        DynamicCalculator dynamicCalculator = new DynamicCalculator(new Item[]{new Item(3), new Item(4), new Item(5),
//                new Item(ItemType.MUL), new Item(ItemType.SUB)});
//        System.out.println(dynamicCalculator.run());
//        dynamicCalculator.stack.display();

        dynamicStackTest();
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
        stack.pop();
        stack.pop();
        stack.pop();
        stack.display();
    }
}
