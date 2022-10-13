import java.security.SecureRandom;
import java.security.Timestamp;
import java.util.Random;

public class Bench<Node> {
    public static void main(String[] args) {
        Long[] result = benchListQueue(500, 1000);
        for (Long long1 : result) {
            System.out.println(long1);
        }

    }

    private static int[] randomUniqueNumbers(int from, int to) {
        int n = to - from + 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        int[] result = new int[n];
        int x = n;
        SecureRandom rd = new SecureRandom();
        for (int i = 0; i < n; i++) {
            // k is a random index in [0,x]
            int k = rd.nextInt(x);
            result[i] = a[k];
            // we got a value from a[k]. we replace its value by the value from the
            // last index so that we will not get that value (a[k]) again
            a[k] = a[x - 1];
            x--;
        }
        return result;
    }

    private static Long[] benchListQueue(int queueSize, int loops) {
        ListQueue listQueue = new ListQueue();
        Long min = Long.MAX_VALUE;
        Long[] timeArray = new Long[queueSize];
        for (int i = 0; i < queueSize; i++) {
            timeArray[i] = min;
        }
        Long timeStart;
        Long timeStop;
        Long time;
        // create an array with random numbers that will populate the list with the same
        // random numbers for each loop
        int[] arrayWithRandomNumbers = randomUniqueNumbers(0, queueSize);
        for (int i = 0; i < loops; i++) {
            for (int j : arrayWithRandomNumbers) {
                listQueue.add(j);
            }
            for (int k = 0; k < queueSize; k++) {
                timeStart = System.nanoTime();
                listQueue.remove();
                timeStop = System.nanoTime();
                time = timeStop - timeStart;
                if (time < timeArray[k]) {
                    timeArray[k] = time;
                }
            }
        }

        return timeArray;
        // return arrayWithRandomNumbers;
    }

}
