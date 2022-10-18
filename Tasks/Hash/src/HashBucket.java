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
        this.mod = mod;
        data = new Node[mod]; // the csv file is 9,675 lines
        this.keys = new int[9676];
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            int code = 0;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                code =  Integer.parseInt(row[0].replaceAll("\\s", ""));
                insert(code, new Node(code, row[1], Integer.valueOf(row[2])));
                // row[0] is code, row[1] is name and row[2] is population
                this.keys[i++] = code;
            }
            max = i - 1; // max is number of zip nodes
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    private void insert(Integer code, Node entry){
     Integer key = code % this.mod;
     Node current = this.data[key];
     Node prev = null;

        while (current != null) {
            if (code.equals(current.code)) {
                current = current.next; //replace the found entry
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

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[15];
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
    public void printKeys() {
        for (int i = 0; i < keys.length; i++) {
            System.out.printf("%d\t", keys[i]);
        }
    }

    public int nrOfCollisions() {
        Set<Integer> store = new HashSet<Integer>();
        int count=0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == 0) {
                System.out.println(0);
                continue;
            }
            if (store.add(keys[i]) == false) {
            count++;
                System.out.println("Duplicate element found : " + keys[i]);
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int mod = 15331;
        HashBucket hash = new HashBucket("/home/adrian/KTH/ID1021-AlgoData/Tasks/Hash/src/postnummer.csv", mod);

        System.out.println("number of collisions for mod="+mod + " is "+hash.nrOfCollisions());
      //  System.out.println(hash.convertToHash("111 12", mod));

        // System.out.println(index);

        hash.collisions(mod);
    }
}
