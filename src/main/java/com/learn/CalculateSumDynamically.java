package com.learn;

import com.learn.consumer.RangeConsumer;
import com.learn.consumer.PartialResultConsumer;
import com.learn.producer.RangeProducer;

/**
 * Created by hackus on 10/10/17.
 */
public class CalculateSumDynamically {
    Thread edgesConsumer;

    PartialResultConsumer result = new PartialResultConsumer();
    Thread partialResultConsumer;

    public CalculateSumDynamically(){
        edgesConsumer = new Thread(new RangeConsumer());
        partialResultConsumer = new Thread(result);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long calculateSum(long [] arrayOfLong) {
        Thread edgesProducer = new Thread(new RangeProducer(arrayOfLong));

        edgesProducer.start();
        edgesConsumer.start();
        partialResultConsumer.start();

        try {
            edgesProducer.join();
            edgesConsumer.join();
            partialResultConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result.getSum();
    }
}
