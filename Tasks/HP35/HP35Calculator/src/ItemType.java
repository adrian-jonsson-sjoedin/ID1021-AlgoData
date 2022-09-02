import java.util.Random;

public enum ItemType {
    ADD,
    SUB,
    MUL,
    DIV,
    VALUE;

    public static ItemType getRandomType() {
        Random random = new Random();
        return values()[random.nextInt(values().length-1)];
    }
}
