import java.util.Arrays;

public class TestUtils {
    public static int sumArray(int[] array){
        int sum = 0;
        for(int i=0;i<array.length;i++){
            sum+=array[i];
        }
        return sum;
    }

    public static int [] getRange(int [] array, int start, int end){
        return Arrays.copyOfRange(array, start, end);
    }


    public static long calculateSumSequentially(long [] arrayOfLong){
        long sum = 0;
        for(int i=0;i<arrayOfLong.length;i++){
            sum+=arrayOfLong[i];
        }
        return sum;
    }

    public static long[] getArray(int length){
        long [] arrayOfLong = new long[length];
        for(int i=0;i<length;i++){
            arrayOfLong[i] = i;
        }
        return arrayOfLong;
    }
}
