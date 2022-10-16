package fb;

import java.util.Arrays;
import java.util.Collections;

public class MaxOfSubArray {

    public static void main(String[] args) {

        int[] arr = { 2, 1, 3, 1, 4, 5, 2, 3, 6 };
                    // 3 3 4 5 5 5 6
        // 1 2 4 6 4 2 1

        int k = 3;
        int i = 0;
        int [] tmp = new int[k];

        tmp = Arrays.copyOfRange(arr, 0, k);
        i = k+1;
        while ( i < arr.length ) {

        }


    }
}
