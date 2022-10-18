public class Test {
    public static void main(String[] arg) {

        Integer a = 123;
        Integer b = 123;

        System.out.println(" a = " + a + " and b = " + b + " and a == b returns " + (a == b));

        Integer x = 1234;
        Integer y = 1234;

        System.out.println(" x = " + x + " and y = " + y + " but x == y returns " + (x == y));

        System.out.println(" so use x.equals(y) that returns " + (x.equals(y)) + " if comparing Integers");
    }

}
