package practise2023;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class MissingRepeatingNumber {
    public static void main(String[] args) {

        int [] arr = {1,3,5,6,4,1};
        //
        int n = arr.length;
        int sum = (n *(n+1))/2;
        int arrSum = Arrays.stream(arr).sum();
        System.out.println(Arrays.toString(arr));
        for(int i=0; i<arr.length; i++) {
            int absv = Math.abs(arr[i]);
            if( arr[absv-1] > 0 )
                arr[absv-1] = -arr[absv-1];
            else {
                System.out.println(absv +" is duplicate");
            }
        }
        for(int i =0; i<arr.length; i++) {
            if(arr[i] >0){
                System.out.println((i+1) +" is missing");
                break;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}