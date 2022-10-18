import java.security.SecureRandom;
import java.util.Random;

public class Bench<Node> {
    public static void main(String[] args) {
        int queueSize = 500;
        int loops = 1;
        int[] rndUniqueNumbArray = randomUniqueNumbers(0, queueSize);// array with unique elements for benchmarking
                                                                     // ArrayQueue

        int benchmark = 3;
        switch (benchmark) {
            case 0 -> {
                System.out.println();
            }
            case 1 -> {
                Long[] resultListQueue = benchListQueue(queueSize, loops); // O(n) to remove

                for (Long long1 : resultListQueue) {
                    System.out.println(long1);
                }
            }
            case 2 -> {
                Long[] resultQueueList = benchQueueList(queueSize, loops); // O(n) to add
                for (Long long2 : resultQueueList) {
                    System.out.println(long2);
                }
            }
            case 3 -> {
                Long[] resultArrayQueueRemove = benchArrayQueueRemove(queueSize, loops, rndUniqueNumbArray);
                for (Long long3 : resultArrayQueueRemove) {
                    System.out.println(long3 / loops);
                }
            }
        }
        // benchPush();
        // benchAdd();
    }

    // depth 0 is root level
    private static void benchPush() {
        Random rand = new Random();
        Heap heap = new Heap<Integer>();
        int[] randomUniqueNumbers = randomUniqueNumbers(0, 100);
        int[] tenTwenty = new int[10];
        for (int i = 0; i < 10; i++) {
            tenTwenty[i] = rand.nextInt(10, 21);// fill with random values between 10-20
        }
        for (int i = 0; i < 64; i++) {// add 64 random number in the interval 0-100 to heap
            heap.add(randomUniqueNumbers[i], randomUniqueNumbers[i]);
        }
        // heap.breathFirstPrint();
        for (int i = 0; i < 20; i++) {
            int increment = tenTwenty[rand.nextInt(10)];
            int depth = heap.push(increment);
            System.out.printf("%d\t %d\n", increment, depth);
        }
    }

    private static void benchAdd() {
        Random rand = new Random();
        Heap heap = new Heap<Integer>();
        int[] randomUniqueNumbers = randomUniqueNumbers(0, 100);
        int[] tenTwenty = new int[10];
        for (int i = 0; i < 10; i++) {
            tenTwenty[i] = rand.nextInt(10, 21);// fill with random values between 10-20
        }
        for (int i = 0; i < 64; i++) {// add 64 random number in the interval 0-100 to heap
            heap.add(randomUniqueNumbers[i], randomUniqueNumbers[i]);
        }
        heap.breathFirstPrint();
        for (int i = 0; i < 20; i++) {
            int increment = tenTwenty[rand.nextInt(10)];
            int depth = heap.add(increment, increment);
            System.out.printf("%d\t %d\n", increment, depth);
        }
        heap.breathFirstPrint();
    }

    // can't get this to work. think the jvm does some kind of optimization which
    // affects the time measurements
    private static Long[] benchArrayQueueRemove(int queueSize, int loops, int[] rndUniqueNumbArray) {
        Long min = Long.MAX_VALUE;
        // create an array to hold the min times from the benchmark
        Long[] timeArray = new Long[queueSize];
        for (int i = 0; i < queueSize; i++) {
            timeArray[i] = min;
        }
        ArrayHeap temp;
        Long timeStart;
        Long timeStop;
        Long time;
        for (int j = 0; j < loops; j++) {
            ArrayHeap arrayList = new ArrayHeap(queueSize);
            for (int i = 0; i < queueSize; i++) {
                int item = rndUniqueNumbArray[i];
                arrayList.add(item);
            }
            for (int k = 0; k < queueSize; k++) {
                timeStart = System.nanoTime();

                System.out.println(arrayList.remove());
                timeStop = System.nanoTime();
                time = timeStop - timeStart;
                // store tim in min time array of we get a lower min value
                if (time < timeArray[k]) {
                    timeArray[k] = time;
                }
            }
            temp = arrayList;
        }
        return timeArray;

    }

    // can't get this to work. think the jvm does some kind of optimization which
    // affects the time measurements
    private static int[] benchArrayQueueAdd(int queueSize, int loops, int[] rndUniqueNumbArray) {
        Long min = Long.MAX_VALUE;
        // create an array to hold the min times from the benchmark
        int[] timeArray = new int[queueSize];
        // for (int i = 0; i < queueSize; i++) {
        // timeArray[i] = 0;
        // }
        ArrayHeap temp;
        Long timeStart;
        Long timeStop;
        Long time;
        for (int j = 0; j < loops; j++) {
            ArrayHeap arrayList = new ArrayHeap(queueSize);
            for (int i = 0; i < queueSize; i++) {
                int item = rndUniqueNumbArray[i];
                timeStart = System.nanoTime();
                arrayList.add(item);
                timeStop = System.nanoTime();
                time = timeStop - timeStart;
                timeArray[i] += time;
                // if (time < timeArray[i]) {
                // timeArray[i] = time;
                // }
            }
            temp = arrayList;
        }
        return timeArray;
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
}
