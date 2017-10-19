package com.attempt1.producer;

import com.attempt1.queues.MyQueues;
import com.attempt1.queues.RangeContainer;

/**
 * Created by hackus on 10/10/17.
 */
public class PartialSumProducer implements Runnable {

    RangeContainer range;
    long sum;

    public PartialSumProducer(RangeContainer range){
        this.range = range;
    }

    public void run() {
        for(int i=range.start;i<range.end;i++){
            sum+=RangeProducer.arrayOfLong[i];
        }

        MyQueues.addValueToPartialResultsQueue(sum);
    }
}
