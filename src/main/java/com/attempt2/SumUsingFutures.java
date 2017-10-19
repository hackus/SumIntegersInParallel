package com.attempt2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
* Source https://dzone.com/articles/java-callable-future-understanding
* */
public class SumUsingFutures {
    int rangeMax;
    ExecutorService executor;

    public SumUsingFutures(int rangeMax){
        executor = Executors.newFixedThreadPool(10);
        this.rangeMax = rangeMax;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Long parallelSum(int n){
        List<Future<Long>> list = new ArrayList<>();

        int numberOfRanges = n/rangeMax;

        long totalSum = 0;

        for(int i=0;i<numberOfRanges;i++){
            Future<Long> future = executor.submit(new PartialAdder(getRange(i*rangeMax,(i+1)*rangeMax)));
            list.add(future);
        }

        for(Future<Long> future : list){
            try {
                totalSum+=future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        return totalSum;
    }

    public MyRange getRange(int current, int n){
        int index = current+rangeMax;
        return index >= n ? new MyRange(current,n) : new MyRange(current,index);
    }
}
