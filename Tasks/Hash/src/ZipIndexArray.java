import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ZipIndexArray {
    Node[] data;

    private class Node {
        Integer code;
        String name;
        Integer population;

        public Node(Integer code, String name, Integer population) {
            this.code = code;
            this.name = name;
            this.population = population;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public Integer getPopulation() {
            return this.population;
        }
    }

    public ZipIndexArray(String file) {
        this.data = new Node[98500]; // 984 99 is the last line in the file we want to read
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            Integer code = null;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[code] = new Node(code, row[1], Integer.valueOf(row[2]));
                // row[0] is code, row[1] is name and row[2] is population
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public String lookup(Integer zipCode) {
        return this.data[zipCode].name;
    }

    public static void main(String[] args) {
        ZipIndexArray zip = new ZipIndexArray("/home/adrian/KTH/ID1021-AlgoData/Tasks/Hash/src/postnummer.csv");

        int k = 1000;
        // warm up so that we hopefully get better benchmark results
        for (int i = 0; i < k; i++) {
            zip.lookup(11115);
        }

        long timeStart = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zip.lookup(11115);
        }
        long timeStop = System.nanoTime();
        System.out.println("Lookup 111 15: " + zip.lookup(11115) + (timeStop - timeStart) / k + "ns");
        // warm up so that we hopefully get better benchmark results
        for (int i = 0; i < k; i++) {
            zip.lookup(98499);
        }

        timeStart = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zip.lookup(98499);
        }
        timeStop = System.nanoTime();
        System.out.println("Lookup 984 99: " + zip.lookup(98499) + (timeStop - timeStart) / k + "ns");
    }

}
