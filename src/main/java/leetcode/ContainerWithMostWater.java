package leetcode;

import java.util.Arrays;

public class ContainerWithMostWater {

    public static void main(String[] args) {

        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(Arrays.toString(arr));

        int[] left2right = new int[arr.length];

        int i = 0;
        int j = arr.length-1;
        int distance = 0;
        while ( i < j ) {
            int tmp = Math.min(arr[i], arr[j]) * (j - i);
            distance = Math.max(distance, tmp);
            if ( arr[i] <= arr[j])
                i++;
            else
                j--;
        }
        System.out.println(distance);


    }
}
