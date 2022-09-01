public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator(new Item[]{new Item(5), new Item(5), new Item(5),
                 new Item(5), new Item(ItemType.ADD), new Item(5), new Item(5), new Item(ItemType.ADD)});
        System.out.println(calc.runStatic());


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
}
