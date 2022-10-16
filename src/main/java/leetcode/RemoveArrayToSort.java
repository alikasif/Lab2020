package leetcode;

import java.util.HashSet;
import java.util.Set;

public class RemoveArrayToSort {

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 10, 4, 2, 3, 5 };
        search(arr);
    }

    static void search(int[] arr) {

        Set<Integer> indices = new HashSet<>();
        int lo = 0;
        int hi = arr.length-1;

        int m = (lo+hi)/2;

        while ( lo < hi ) {

            if ( (arr[m] > arr[m-1] && arr[m] > arr[m+1]) ||
                    (arr[m] < arr[m-1] && arr[m] < arr[m+1]) ) {
                indices.add(m);
                break;
            }
            //if()
            hi = m+1;
        }
        System.out.println(indices);
    }
}
