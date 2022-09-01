
public class Calculator {
    Item[] expr;
    int instructionPointer;
    Stack stack;

    public Calculator(Item[] expr) {
        this.expr = expr;
        this.instructionPointer = 0;
        this.stack = new Stack();
    }



    public int runStatic() {
        while (instructionPointer < expr.length) {
            step();
        }
        return stack.pop();
    }

    public void step() {
        Item next = expr[instructionPointer++];
        switch (next.getType()) {
            case ADD -> {
                int y = stack.pop();
                int x = stack.pop();
                int res = x +y;
                stack.push(res);
            }
            case SUB -> {
                int y = stack.pop();
                int x = stack.pop();
                int res = x-y;
                stack.push(res);
            }
            case DIV -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x / y);
            }
            case MUL -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x * y);
            }
            case VALUE -> {
                stack.push(next.getValue());
            }
        }
    }
}
