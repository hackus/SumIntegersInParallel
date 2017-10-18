package com.learn;

import com.learn.queues.MyQueues;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hackus on 10/10/17.
 */
public class SystemParams {

    volatile static int finalNumberOfRanges = 0;
    volatile static int currentNumberOfRanges = -1;

    public static boolean isSumDone(){
        return currentNumberOfRanges == finalNumberOfRanges;
    }

    public static void setFinalNumberOfRanges(int n){
        finalNumberOfRanges = n;
    }

    public static void setCurrentNumberOfRanges(int n){
        currentNumberOfRanges = n;
    }
}
