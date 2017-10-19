import com.attempt1.CalculateSumWithPredefinedThreads;
import com.attempt1.CalculateSumDynamically;
import com.attempt2.SessionParams;
import com.attempt2.SumUsingFutures;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by hackus on 10/11/17.
 */
public class SumTest {

    int n = 100_000_000;
    long[] arrayOfLong = TestUtils.getArray(n);

    @Test
    public void testCalculateSumDynamically(){
        CalculateSumDynamically calculatedSum = new CalculateSumDynamically();

        long start = System.nanoTime();
        long parallel = calculatedSum.calculateSum(arrayOfLong);
        long end = System.nanoTime();
        System.out.println("parallel duration is:   " + (end - start)/1000);

        start = System.nanoTime();
        long sequential = TestUtils.calculateSumSequentially(arrayOfLong);
        end = System.nanoTime();
        System.out.println("sequential duration is: " + (end - start)/1000);

        System.out.println("sequential is: " + sequential);
        System.out.println("parallel is:   " + parallel);

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
        System.out.println("parallel is:   " + parallel);

        assertTrue(sequential[0] == parallel);
    }

    @Test
    public void calculateSumUsinFuture(){
        SumUsingFutures calculatedSum = new SumUsingFutures(10_000_000);
        SessionParams.setArray(arrayOfLong);

        long start = System.nanoTime();
        long parallel = calculatedSum.parallelSum(arrayOfLong.length);
        long end = System.nanoTime();
        System.out.println("parallel duration is:   " + (end - start)/1000);

        start = System.nanoTime();
        long sequential = TestUtils.calculateSumSequentially(arrayOfLong);
        end = System.nanoTime();
        System.out.println("sequential duration is: " + (end - start)/1000);

        System.out.println("sequential is: " + sequential);
        System.out.println("parallel is:   " + parallel);

        assertTrue(sequential == parallel);
    }

//    @Test
    public void testArray(){
        int [] array = {1,2,3,4,5};
        int sum = 0;
        for(int i=0;i<array.length;i++){
            sum+=array[i];
        }
        assertTrue(sum == 15);

        sum = TestUtils.sumArray(TestUtils.getRange(array, 0, 2));
        sum += TestUtils.sumArray(TestUtils.getRange(array, 2, array.length));
        assertTrue(sum == 15);
    }


}
