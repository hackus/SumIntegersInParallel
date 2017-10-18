import com.learn.CalculateSumWithPredefinedThreads;
import com.learn.CalculateSumDynamically;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by hackus on 10/11/17.
 */
public class CalculateSumTest {

    int n = 100_000_000;
    long[] arrayOfLong = getArray(n);

    @Test
    public void testCalculateSumDynamically(){
        CalculateSumDynamically calculatedSum = new CalculateSumDynamically();

        long start = System.nanoTime();
        long parallel = calculatedSum.calculateSum(arrayOfLong);
        long end = System.nanoTime();
        System.out.println("parallel duration is:   " + (end - start)/1000);

        start = System.nanoTime();
        long sequential = calculateSumSequentially(arrayOfLong);
        end = System.nanoTime();
        System.out.println("sequential duration is: " + (end - start)/1000);

        System.out.println("sequential is: " + sequential);
        System.out.println("parallel is: " + parallel);

        assertTrue(sequential == parallel);
    }

    @Test
    public void testCalculateWithPredefinedThreads(){
        CalculateSumWithPredefinedThreads paraleleSum = new CalculateSumWithPredefinedThreads();

        final long[] sequential = {0};
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {

                for(int i=0;i<arrayOfLong.length;i++){
                    sequential[0] +=arrayOfLong[i];
                }
            }
        });

        long start = System.nanoTime();
        long parallel = paraleleSum.calculateSum(arrayOfLong);
        long end = System.nanoTime();
        System.out.println("parallel duration is:   " + (end - start)/1000);

        start = System.nanoTime();
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.nanoTime();
        System.out.println("sequential duration is: " + (end - start)/1000);

        System.out.println("sequential is: " + sequential[0]);
        System.out.println("parallel is: " + parallel);

        assertTrue(sequential[0] == parallel);
    }



    public long calculateSumSequentially(long [] arrayOfLong){
        long sum = 0;
        for(int i=0;i<arrayOfLong.length;i++){
            sum+=arrayOfLong[i];
        }
        return sum;
    }

    private long[] getArray(int length){
        long [] arrayOfLong = new long[length];
        for(int i=0;i<length;i++){
            arrayOfLong[i] = i;
        }
        return arrayOfLong;
    }

//    @Test
    public void testArray(){
        int [] array = {1,2,3,4,5};
        int sum = 0;
        for(int i=0;i<array.length;i++){
            sum+=array[i];
        }
        assertTrue(sum == 15);

        sum = sumArray(getRange(array, 0, 2));
        sum += sumArray(getRange(array, 2, array.length));
        assertTrue(sum == 15);
    }

    public int sumArray(int[] array){
        int sum = 0;
        for(int i=0;i<array.length;i++){
            sum+=array[i];
        }
        return sum;
    }

    public int [] getRange(int [] array, int start, int end){
        return Arrays.copyOfRange(array, start, end);
    }
}
