import java.util.LinkedList;

public class City {
    private String name;
    private LinkedList<Connection> neighbors;

    public City(String name) {
        this.name = name;
        this.neighbors = new LinkedList<Connection>();
    }

    public void connect(City next, int distance) {
        this.neighbors.add(new Connection(next, distance));
    }

    public String getName() {
        return this.name;
    }

    public LinkedList<Connection> getNeighbor() {
        return this.neighbors;
    }
}
