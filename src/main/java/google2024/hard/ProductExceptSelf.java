package google2024.hard;

import java.util.Arrays;

public class ProductExceptSelf {
    public static void main(String[] args) {

        int[] arr = {1,2,3,4};
        int [] left = new int[arr.length];
        int [] right = new int[arr.length];

        left[0] = 1;
        int i =1;
        while (i < arr.length) {
            left[i] = left[i-1] * arr[i-1];
            i++;
        }

        i = arr.length-2;
        right[arr.length-1] = 1;
        while (i >= 0) {
            right[i] = right[i+1] * arr[i+1];
            i--;
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

    }
}
