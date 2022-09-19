import java.util.List;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        LinkedList list2 = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.display();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list.appendFirst(list2);
        list.display();
    }
}