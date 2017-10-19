package com.attempt2;

import java.util.concurrent.Callable;

public class PartialAdder implements Callable<Long> {
    MyRange range;

    public PartialAdder(MyRange range){
        this.range = range;
    }

    public Long call() {
        long partialSum=0;
        for(int i=range.getStart();i<range.getEnd();i++){
            partialSum+=SessionParams.getArray()[i];
        }
        return partialSum;
    }
}
