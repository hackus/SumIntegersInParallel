package com.attempt1;

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
