public class Naive {
    private static Integer shortest(City from, City to, Integer max) {
        if (max < 0) {
            return null;
        }
        if (from == to) {
            return 0;
        }
        Integer shrt = null;
        for (int i = 0; i < from.getNeighbor().size(); i++) {
            if (from.getNeighbor().get(i) != null) {
                Connection conn = from.getNeighbor().get(i);
                Integer distance = shortest(conn.getConnectingCity(), to, max - conn.getDistance());
                if ((distance != null) && ((shrt == null) || (shrt > distance + conn.getDistance()))) {
                    shrt = distance + conn.getDistance();
                }
                if ((shrt != null) && (max > shrt)) {
                    max = shrt;
                }
            }
        }
        return shrt;
    }

    public static void main(String[] args) {
        String[] input = new String[4];
        int cities = 7;
        switch (cities) {
            case 0 -> {
                input[0] = "Malmö";
                input[1] = "Göteborg";
                input[2] = "500";
            }
            case 1 -> {
                input[0] = "Göteborg";
                input[1] = "Stockholm";
                input[2] = "500";
            }
            case 2 -> {
                input[0] = "Malmö";
                input[1] = "Stockholm";
                input[2] = "500";
            }
            case 3 -> {
                input[0] = "Stockholm";
                input[1] = "Sundsvall";
                input[2] = "500";
            }
            case 4 -> {
                input[0] = "Stockholm";
                input[1] = "Umeå";
                input[2] = "700";
            }
            case 5 -> {
                input[0] = "Göteborg";
                input[1] = "Sundsvall";
                input[2] = "600";
            }
            case 6 -> {
                input[0] = "Sundsvall";
                input[1] = "Umeå";
                input[2] = "500";
            }
            case 7 -> {
                input[0] = "Umeå";
                input[1] = "Göteborg";
                input[2] = "1000";
            }
            case 8 -> {
                input[0] = "Göteborg";
                input[1] = "Umeå";
                input[2] = "1000";
            }
        }
        Map map = new Map("/home/adrian/KTH/ID1021-AlgoData/Tasks/Graphs/src/trains.csv");
        Integer max = Integer.valueOf(input[2]);
        // System.out.println(max);
        long t0 = System.nanoTime();
        Integer distance = shortest(map.lookupOrAdd(input[0]), map.lookupOrAdd(input[1]), max);
        long t1 = System.nanoTime();

        if (distance != null) {
            System.out.println("quickest route from " + input[0] + " to " + input[1] + " : "
                    + distance + " minutes, found in " + ((t1 - t0) / 1_000_000) + "ms");
        } else {
            System.out.println("No path found - increase path");
        }
    }
}
