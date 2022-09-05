import java.util.Random;

public class Test {
    public static void main(String[] args) throws Exception{
        System.out.println("Resolution " + search(50000) + " ns");
    }

    public static double search(int n){

        double t_total = 0;

        Random rnd = new Random();

        int[] keys = new int[n];
        int[] array = new int[n];

        for(int i = 0; i < n; i++){
            keys[i] = rnd.nextInt(10*n);
        }

        int sum = 0;

        for(int i = 0; i < n; i++){
            array[i] = rnd.nextInt(10*n);
        }

        long t0 = System.nanoTime();
        for(int ki = 0; ki < n; ki++){
            int key = keys[ki];
            for(int i = 0; i < n; i++){
                if(array[i] == key){
                    sum++;
                }
            }
        }
        double t1 = System.nanoTime();
        return (t1 - t0);
    }
}


