package google2024.medium;

import java.util.Arrays;

public class ProductAndSumArrayExceptSelf {
    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5};
        sumExceptSelf(arr);
        int[] arr2 = {1,2,3,4,5};
        productExceptSelf(arr2);
    }
    static void sumExceptSelf(int[] arr) {
        int[] ans = new int[arr.length];
        Arrays.fill(ans, 0);
        int sum = 0;

        for(int i=0; i<arr.length; i++) {
            sum = sum+arr[i];
        }

        for(int i=0; i<arr.length; i++) {
            arr[i] = sum - arr[i];
        }

        System.out.println(Arrays.toString(arr));
    }

    static void productExceptSelf(int[] arr) {
        int[] ans = new int[arr.length];
        Arrays.fill(ans, 0);
        int sum = 1;

        for(int i=0; i<arr.length; i++) {
            sum = sum*arr[i];
        }

        for(int i=0; i<arr.length; i++) {
            arr[i] = sum/arr[i];
        }

        System.out.println(Arrays.toString(arr));
    }

}
