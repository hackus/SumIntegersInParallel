package com.learn.consumer;

import com.learn.SystemParams;
import com.learn.producer.PartialSumProducer;
import com.learn.queues.MyQueues;
import com.learn.queues.RangeContainer;

/**
 * Created by hackus on 10/10/17.
 */
public class RangeConsumer implements Runnable {

    public void run() {
        while(!SystemParams.isSumDone()) {
            final RangeContainer range = MyQueues.queueOfArrayOfLong.poll();
            if(range == null) continue;
            Thread t = new Thread(new PartialSumProducer(range));
            t.start();
        }
    }
}
