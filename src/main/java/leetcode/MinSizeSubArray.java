package leetcode;

import java.util.Arrays;

public class MinSizeSubArray {

    public static void main(String[] args) {

        int[] arr = {2, 3, 1, 2, 4, 3};
        int s = 7;
        int t = 0;
        int s2 = 0;
        int[] sums = new int[arr.length + 1];

/*
        for (int i = 1; i < arr.length; i++)
            sums[i] = sums[i - 1] + arr[i - 1];

        System.out.println(Arrays.toString(sums));
*/

        for (int i= 0; i < arr.length; i++) {

            t += arr[i];

            while ( s2 < i && t >= s ) {
                t -= arr[s2];
                s2++;
                System.out.println("sum: "+ t +" "+s2+" "+i);
            }
        }
    }
}