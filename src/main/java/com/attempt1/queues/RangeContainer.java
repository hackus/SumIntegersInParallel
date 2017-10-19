package com.attempt1.queues;

/**
 * Created by hackus on 10/11/17.
 */
public class RangeContainer {
    public int start;
    public int end;
    public RangeContainer(int start, int end, int max){
        this.start = start > max ? max : start;
        this.end = end > max ? max : end;
    }
}
