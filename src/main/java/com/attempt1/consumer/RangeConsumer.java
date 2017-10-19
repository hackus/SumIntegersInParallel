package com.attempt1.consumer;

import com.attempt1.SystemParams;
import com.attempt1.producer.PartialSumProducer;
import com.attempt1.queues.MyQueues;
import com.attempt1.queues.RangeContainer;

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
