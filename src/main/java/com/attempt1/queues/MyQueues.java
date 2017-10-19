package com.attempt1.queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hackus on 10/10/17.
 */
public class MyQueues {
    public static BlockingQueue<Long> queueOfPartialResults = new ArrayBlockingQueue(1000);

    public static BlockingQueue<RangeContainer> queueOfArrayOfLong = new ArrayBlockingQueue(1000);

    public static void addValueToPartialResultsQueue(long sum){
        try {
            queueOfPartialResults.put(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
