import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Map {
    private City[] cities;
    private final int mod = 541;

    public Map(String file) {
        cities = new City[mod];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                City city = lookupOrAdd(row[0]);
                City neighboringCity = lookupOrAdd(row[1]);
                Integer distance = Integer.valueOf(row[2]);
                city.connect(neighboringCity, distance);
                neighboringCity.connect(city, distance);
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private Integer hash(String name) {
        Integer hash = 7;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }

        return hash % mod;
    }

    public City lookupOrAdd(String name) {
        Integer index = hash(name);
        while (true) {
            if (cities[index] == null) {
                break;
            }
            if (cities[index].getName().equals(name)) {
                return cities[index];
            }
            index = index + 1 % mod;
        }
        City city = new City(name);
        cities[index] = city;
        return city;
    }

    public static void main(String[] args) {

        Map map = new Map("/home/adrian/KTH/ID1021-AlgoData/Tasks/Graphs/src/trains.csv");
        for (int i = 0; i < map.mod; i++) {
            if (map.cities[i] != null) {
                City city = map.cities[i];
                int cnx = 0;
                for (int j = 0; j < city.getNeighbor().size(); j++) {
                    if (city.getNeighbor().get(j) != null) {
                        cnx++;
                    }
                }
                System.out.println("(" + cnx + ")" + map.cities[i].getName() + ":");
                for (int j = 0; j < city.getNeighbor().size(); j++) {
                    if (city.getNeighbor().get(j) != null) {
                        System.out.println(city.getNeighbor().get(j).getConnectingCity().getName() + ", ");
                    }
                }
                System.out.println("");
            }
        }
    }
}