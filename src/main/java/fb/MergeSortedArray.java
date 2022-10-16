package fb;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MergeSortedArray {

    public static void main(String[] args) {

        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {0, 2, 6, 8, 9};

        int i = arr1.length-1;
        int j = arr2.length-1;
        int x = j;
        while ( i >= 0 && j >= 0 ) {

            System.out.println(Arrays.toString(arr1) +" " + i);
            System.out.println(Arrays.toString(arr2) +"  "+j);
            System.out.println("####################");

            while ( j >= 0 && arr1[i] < arr2[j] ) {
                j--;
            }
            if ( i>=0 && j >=0 ) {
                int t = arr1[i];
                arr1[i] = arr2[j];
                arr2[j] = t;
                Arrays.sort(arr1);
            }
        }
    }
}
