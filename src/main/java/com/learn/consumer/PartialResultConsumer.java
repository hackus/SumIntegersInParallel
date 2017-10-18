package com.learn.consumer;

import com.learn.SystemParams;
import com.learn.queues.MyQueues;

/**
 * Created by hackus on 10/10/17.
 */
public class PartialResultConsumer implements Runnable {

    private long sum;
    public int partialRangeIndex=1;

    public void run() {
        while(!SystemParams.isSumDone()) {
            Long partialSum = MyQueues.queueOfPartialResults.poll();
            if(partialSum == null) continue;
            sum+=partialSum;
            SystemParams.setCurrentNumberOfRanges(partialRangeIndex++);
        }
    }

    public long getSum(){
        return sum;
    }
}
