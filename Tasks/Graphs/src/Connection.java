public class Connection {
    private final City connectingCity;
    private final int distance;

    public Connection(City connectingCity, int distance) {
        this.connectingCity = connectingCity;
        this.distance = distance;
    }

    public City getConnectingCity() {
        return this.connectingCity;
    }

    public int getDistance() {
        return this.distance;
    }
}
