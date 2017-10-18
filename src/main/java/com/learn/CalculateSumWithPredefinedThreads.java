package com.learn;

import com.learn.consumer.PartialResultConsumer;
import com.learn.consumer.ParallelRangeConsumer;
import com.learn.producer.RangeProducer;

/**
 * Created by hackus on 10/10/17.
 */
public class CalculateSumWithPredefinedThreads {
    Thread rangeProducer;
    Thread rangeParalelConsumer1;
    Thread rangeParalelConsumer2;
    Thread rangeParalelConsumer3;
    Thread rangeParalelConsumer4;
    Thread rangeParalelConsumer5;
    PartialResultConsumer result;
    Thread partialResultConsumer;

    public CalculateSumWithPredefinedThreads() {
        rangeParalelConsumer1 = new Thread(new ParallelRangeConsumer());
        rangeParalelConsumer2 = new Thread(new ParallelRangeConsumer());
        rangeParalelConsumer3 = new Thread(new ParallelRangeConsumer());
        rangeParalelConsumer4 = new Thread(new ParallelRangeConsumer());
        rangeParalelConsumer5 = new Thread(new ParallelRangeConsumer());

        rangeParalelConsumer1.start();
        rangeParalelConsumer2.start();
        rangeParalelConsumer3.start();
        rangeParalelConsumer4.start();
        rangeParalelConsumer5.start();

        result = new PartialResultConsumer();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long calculateSum(long[] arrayOfLong) {
        rangeProducer = new Thread(new RangeProducer(arrayOfLong));
        partialResultConsumer = new Thread(result);

        rangeProducer.start();
        partialResultConsumer.start();

        try {
            rangeProducer.join();
            partialResultConsumer.join();
            rangeParalelConsumer1.join();
            rangeParalelConsumer2.join();
            rangeParalelConsumer3.join();
            rangeParalelConsumer4.join();
            rangeParalelConsumer5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result.getSum();
    }
}
