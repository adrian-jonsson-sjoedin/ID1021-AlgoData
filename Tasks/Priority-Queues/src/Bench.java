import java.security.SecureRandom;
import java.security.Timestamp;
import java.util.Random;

public class Bench<Node> {
    public static void main(String[] args) {
        Long[] resultListQueue = benchListQueue(500, 1000);
        Long[] resultQueueList = benchQueueList(500, 1000);
        int benchmark = 2;
        switch (benchmark) {
            case 1 -> {
                for (Long long1 : resultListQueue) {
                    System.out.println(long1);
                }
            }
            case 2 -> {
                for (Long long2 : resultQueueList) {
                    System.out.println(long2);
                }
            }
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
        Long min = Long.MAX_VALUE;
        // create an array to hold the min times from the benchmark
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
            ListQueue listQueue = new ListQueue();
            // fill up the list with the random items. will give us a new list every loop
            // but with the same items on the same spot
            for (int j : arrayWithRandomNumbers) {
                listQueue.add(j);
            }
            // start emptying the array while measuring the time for each remove
            for (int k = 0; k < queueSize; k++) {
                timeStart = System.nanoTime();
                listQueue.remove();
                timeStop = System.nanoTime();
                time = timeStop - timeStart;
                // store tim in min time array of we get a lower min value
                if (time < timeArray[k]) {
                    timeArray[k] = time;
                }
            }
        }
        return timeArray;
    }

    private static Long[] benchQueueList(int queueSize, int loops) {
        Long min = Long.MAX_VALUE;
        // create an array to hold the min times from the benchmark
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
            QueueList queueList = new QueueList();

            for (int j = 0; j < queueSize; j++) {
                int item = arrayWithRandomNumbers[j];
                timeStart = System.nanoTime();
                queueList.add(item);
                timeStop = System.nanoTime();
                time = timeStop - timeStart;
                if (time < timeArray[j]) {
                    timeArray[j] = time;
                }
            }
        }
        return timeArray;
    }
}
