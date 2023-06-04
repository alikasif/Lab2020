package salesforce;

import java.util.Arrays;

public class SubArrayOfSizeKAndThresHold {
    public static void main(String[] args) {

        int[] arr = {2,2,2,2,5,5,5,8};
        int k = 3;
        int threshold = 4;
        int start = -1;
        int sum=0;
        int c=0;
        int c1=0;

        for(int i=0; i<arr.length; i++) {

            if(c < k) {

                if(start == -1)
                    start = i;

                sum = sum + arr[i];
                c++;

            }
            else {
                if( (sum/k) >= threshold) {
                    System.out.println(Arrays.toString(Arrays.copyOfRange(arr, start,  i)));
                    c1++;
                }
                sum = sum - arr[start] + arr[i];
                start++;
                //c--;
            }
        }
        if( (sum/k) >= threshold) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(arr, start,  arr.length)));
            c1++;
        }
    }
}