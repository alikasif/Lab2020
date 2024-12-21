package google2024.hard;

import java.util.Arrays;

//https://leetcode.com/problems/count-of-smaller-numbers-after-self/description/
public class SmallerNumberAfterSelf {

    public static void main(String[] args) {

        int[] arr = {5,2,0,1};
        int[] smaller = new int[arr.length];
        smaller[arr.length-1] = 0;

        for(int i=arr.length-2; i>=0; i--) {
            if(arr[i] > arr[i+1])
                smaller[i] = smaller[i+1]+1;
            else
                smaller[i] = smaller[i+1];
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(smaller));
    }
}
