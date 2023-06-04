package salesforce;

import java.util.Arrays;

public class MinSizeSubArraySum {
    public static void main(String[] args) {

        int target = 7;
        int[] arr = {2, 3, 1, 2, 4, 3};

        int s=0;
        int mi=0;
        for(int i=0; i<arr.length; i++) {
            s = s + arr[i];

            if( s == target) {
                System.out.println(Arrays.toString(Arrays.copyOfRange(arr, mi, i+1)));
            }
            else {
                while(mi < i && s > target) {
                    s = s - arr[mi];
                    mi++;
                }
            }
            if( s == target) {
                System.out.println(Arrays.toString(Arrays.copyOfRange(arr, mi, i+1)));
            }

        }
    }
}
