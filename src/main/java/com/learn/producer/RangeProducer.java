package com.learn.producer;

import com.learn.SystemParams;
import com.learn.queues.MyQueues;
import com.learn.queues.RangeContainer;

import java.util.Arrays;

/**
 * Created by hackus on 10/10/17.
 */
public class RangeProducer implements Runnable {

    int endNumber;
    int defaultRange = 20_000_000;
    static long[] arrayOfLong;
    int numberOfRanges;

    public RangeProducer(long[] arrayOfLongValue){
        arrayOfLong = arrayOfLongValue;
        this.endNumber = arrayOfLong.length;
        this.numberOfRanges = endNumber/defaultRange+1;

        SystemParams.setFinalNumberOfRanges(this.numberOfRanges);
    }

    public static long[] getArrayOfLong(){
        return arrayOfLong;
    }

    public void run() {
        for(int i = 0; i<numberOfRanges;i++){
            try {
//                MyQueues.queueOfArrayOfLong.put(Arrays.copyOfRange(arrayOfLong, i*defaultRange, (i+1)*defaultRange));
                MyQueues.queueOfArrayOfLong.put(new RangeContainer(i*defaultRange, (i+1)*defaultRange, endNumber));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
