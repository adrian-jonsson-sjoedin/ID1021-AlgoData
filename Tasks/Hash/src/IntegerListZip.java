
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class IntegerListZip {
    Node[] data;
    int max;

    private class Node {
        private Integer code;
        private String name;
        private Integer population;

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

    /**
     * Takes a csv zip file and saves each entry as a node in a Node array.
     * 
     * @param fileName
     */
    public IntegerListZip(String fileName) {
        data = new Node[10_000]; // the csv file is 9,675 lines
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            Integer code = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
                // row[0] is code, row[1] is name and row[2] is population
            }
            max = i - 1; // max is number of zip nodes
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public String linearLookup(Integer code) {
        for (int i = 0; i < data.length; i++) {
            if (code.equals(data[i].code)) {
                return data[i].name;
            }
        }
        return null;
    }

    public String binaryLookup(Integer zip) {
        int min = 0;
        int mx = this.max;
        while (true) {
            int index = (min + mx) / 2;
            int compare = zip.compareTo(data[index].code);// returns 0 if they are equal
            if (compare == 0) {
                return data[index].name;
            }
            if (compare > 0 && index < mx) {
                min = index + 1;
                continue;
            }
            if (compare < 0 && index > min) {
                mx = index - 1;
                continue;
            }
            break;
        }
        return null;
    }

    public static void main(String[] args) {
        // read the zip file and store it in the data field
        IntegerListZip zip = new IntegerListZip("/home/adrian/KTH/ID1021-AlgoData/Tasks/Hash/src/postnummer.csv");

        int k = 1000;
        // warm up so that we hopefully get better benchmark results
        for (int i = 0; i < k; i++) {
            zip.linearLookup(11115);
        }

        long timeStart = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zip.linearLookup(11115);
        }
        long timeStop = System.nanoTime();
        System.out.println("linear 111 15: " + zip.linearLookup(11115) + (timeStop - timeStart) / k + "ns");

        // warm up so that we hopefully get better benchmark results
        for (int i = 0; i < k; i++) {
            zip.linearLookup(98499);
        }

        timeStart = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zip.linearLookup(98499);
        }
        timeStop = System.nanoTime();
        System.out.println("linear 984 99: " + zip.linearLookup(98499) + (timeStop - timeStart) / k + "ns");

        // warm up so that we hopefully get better benchmark results
        for (int i = 0; i < k; i++) {
            zip.binaryLookup(11115);
        }

        timeStart = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zip.binaryLookup(11115);
        }
        timeStop = System.nanoTime();
        System.out.println("binary 111 15: " + zip.binaryLookup(11115) + (timeStop - timeStart) / k + "ns");

        // warm up so that we hopefully get better benchmark results
        for (int i = 0; i < k; i++) {
            zip.binaryLookup(98499);
        }

        timeStart = System.nanoTime();
        for (int i = 0; i < k; i++) {
            zip.binaryLookup(98499);
        }
        timeStop = System.nanoTime();
        System.out.println("binary 984 99: " + zip.binaryLookup(98499) + (timeStop - timeStart) / k + "ns");
    }
}
