package com.attempt1.consumer;

import com.attempt1.SystemParams;
import com.attempt1.producer.RangeProducer;
import com.attempt1.queues.MyQueues;
import com.attempt1.queues.RangeContainer;

/**
 * Created by hackus on 10/11/17.
 */
public class ParallelRangeConsumer implements Runnable {

    public void run(){
        while(!SystemParams.isSumDone()) {
            final RangeContainer range = MyQueues.queueOfArrayOfLong.poll();
            if(range == null) continue;
            long sum = 0;

//            System.out.println(String.format("start %d end %d", range.start, range.end));
            for(int i=range.start;i<range.end;i++){
                sum+=RangeProducer.getArrayOfLong()[i];
            }

            MyQueues.addValueToPartialResultsQueue(sum);
        }
    }
}
