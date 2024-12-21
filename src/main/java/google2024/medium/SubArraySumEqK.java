package google2024.medium;

import java.util.Arrays;

public class SubArraySumEqK {
    public static void main(String[] args) {

        int[] arr = {1,2,3};
        int k = 3;

        int j=0;
        int i=0;
        int sum = 0;
        while ( i < arr.length) {
            sum = sum + arr[i];
            if(sum == k){
                System.out.println(j +"|"+ i);
                System.out.println(Arrays.toString(Arrays.copyOfRange(arr, j, i+1)));
            }
            while (sum > k) {
                sum = sum - arr[j];
                j++;
            }
            //System.out.println(Arrays.toString(Arrays.copyOfRange(arr, j, i)));
            i++;
        }
        if(sum == k){
            System.out.println(j +"|"+ i);
            System.out.println(Arrays.toString(Arrays.copyOfRange(arr, j, i+1   )));
        }
    }
}
