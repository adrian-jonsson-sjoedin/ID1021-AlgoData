public class Connection {
    private final City connectingCity;
    private final Integer distance;

    public Connection(City connectingCity, Integer distance) {
        this.connectingCity = connectingCity;
        this.distance = distance;
    }

    public City getConnectingCity() {
        return this.connectingCity;
    }

    public Integer getDistance() {
        return this.distance;
    }
}
