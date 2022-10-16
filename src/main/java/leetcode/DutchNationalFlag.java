package leetcode;

import java.util.Arrays;

public class DutchNationalFlag {

    public static void main(String[] args) {

        int[] arr = {0, 1, 2, 0, 2, 1, 1};
        // 0 1 1 0 2 1 1

        int i = 0, k = 1, j = arr.length-1;

        while (k < j){
            System.out.println(Arrays.toString(arr));
            if(arr[k] == 2) {
                int t = arr[k];
                arr[k] = arr[j];
                arr[j] = t;
                j--;
            }

            if(arr[k] == 0) {
                int t = arr[k];
                arr[k] = arr[i];
                arr[i] = t;
                i++;
            }
            else
                k++;
        }
        System.out.println(Arrays.toString(arr));
    }
}
