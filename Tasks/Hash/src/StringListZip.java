import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StringListZip {
    Node[] data;
    int max;

    private class Node {
        private String code;
        private String name;
        private Integer population;

        public Node(String code, String name, Integer population) {
            this.code = code;
            this.name = name;
            this.population = population;
        }

        public String getCode() {
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
    public StringListZip(String fileName) {
        data = new Node[10_000]; // the csv file is 9,675 lines
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;

            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
                // row[0] is code, row[1] is name and row[2] is population
            }
            max = i - 1; // max is number of zip nodes
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public String linearLookup(String code) {
        for (int i = 0; i < data.length; i++) {
            if (code.equals(data[i].code)) {
                return data[i].name;
            }
        }
        return null;
    }

    public String binaryLookup(String zip) {
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
        StringListZip zip = new StringListZip("/home/adrian/KTH/ID1021-AlgoData/Tasks/Hash/src/postnummer.csv");
        System.out.println(zip.binaryLookup("132 34"));

    }
}
