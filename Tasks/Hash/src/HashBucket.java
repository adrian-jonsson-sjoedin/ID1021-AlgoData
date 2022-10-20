import javax.naming.ldap.HasControls;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class HashBucket {
    Node[] data;
    int mod;
    int[] keys;
    int max;

    private class Node {
        private Integer code;
        private String name;
        private Integer population;
        private Node next;

        public Node(Integer code, String name, Integer population) {
            this.code = code;
            this.name = name;
            this.population = population;
            this.next = null;
        }
    }

    public HashBucket(String file, int mod) {
        this.mod = mod; // save the modulo so that it can be used between methods
        data = new Node[mod]; // make the data array that will hold the nodes the size of modulo that will be
                              // used to hash the keys.
        this.keys = new int[9676];// the csv file is 9,675 lines
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            int code = 0;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code = Integer.parseInt(row[0].replaceAll("\\s", ""));
                insert(code, new Node(code, row[1], Integer.valueOf(row[2])));
                // row[0] is code, row[1] is name and row[2] is population
                this.keys[i++] = code;

            }
            max = i - 1; // max is number of zip nodes
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private void insert(Integer code, Node entry) {
        Integer key = code % this.mod; // convert the zip code to a simple hash using modulo
        Node current = this.data[key];
        Node prev = null;

        while (current != null) {// if there already is a node placed at the hash index we check if they are the
                                 // same and if they are we replace the found entry
            if (code.equals(current.code)) {
                current = current.next; // replace the found entry
                break;
            }
            prev = current;
            current = current.next;
        }
        if (prev != null) {
            prev.next = entry;
        } else {
            data[key] = entry;
        }
        entry.next = current;
    }

    public String lookup(Integer key) {
        Integer index = key % this.mod;
        Node current = data[index];
        while (current != null) {
            if (key.equals(current.code))
                return current.name;
            current = current.next;
        }
        return null;
    }

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];
        for (int i = 0; i < max; i++) {
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }
        System.out.print(mod);
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public int nrOfCollisions() {
        Set<Integer> store = new HashSet<Integer>();
        int count = 0;
        for (int i = 0; i < keys.length; i++) {

            if (store.add(keys[i] % this.mod) == false) {
                count++;
                // System.out.println("Duplicate element found : " + keys[i]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int mod = 31327;
        // 31327 8961 688 25 0 0 0 0 0 0 0. 714 collisions
        // 28627 8878 770 26 0 0 0 0 0 0 0
        // 27773 8820 841 13 0 0 0 0 0 0 0
        HashBucket hash = new HashBucket("/home/adrian/KTH/ID1021-AlgoData/Tasks/Hash/src/postnummer.csv", mod);

        System.out.println("number of collisions for mod=" + mod + " is " +
                hash.nrOfCollisions());

        hash.collisions(mod);

        int k = 1000;
        // warm up so that we hopefully get better benchmark results
        for (int i = 0; i < k; i++) {
            hash.lookup(11115);
        }

        long timeStart = System.nanoTime();
        for (int i = 0; i < k; i++) {
            hash.lookup(11115);
        }
        long timeStop = System.nanoTime();
        System.out.println("Lookup 111 15: " + hash.lookup(11115) + (timeStop - timeStart) / k + "ns");

        // warm up so that we hopefully get better benchmark results
        for (int i = 0; i < k; i++) {
            hash.lookup(98499);
        }

        timeStart = System.nanoTime();
        for (int i = 0; i < k; i++) {
            hash.lookup(98499);
        }
        timeStop = System.nanoTime();
        System.out.println("Lookup 984 99: " + hash.lookup(98499) + (timeStop - timeStart) / k + "ns");
    }
}
