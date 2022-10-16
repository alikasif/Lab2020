package leetcode;

import java.util.HashSet;
import java.util.Set;

public class MatrixSearch {

    public static void main(String[] args) {

        int[][] arr = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };

        int k = 20;

        int i = 0;
        int j = 0;

        int s = 4;
        Set<Integer> rows = new HashSet<>();
        while (i <= s) {
            if (arr[i][j] <= k && arr[i][j+s] >= k) {
                rows.add(i);
            }
            i= i+1;
        }
        System.out.println(rows);
        for (int l : rows) {
            System.out.println("searching: "+l);
            binarySearch(arr[l], k, 0, arr[l].length);
        }
    }

    static void binarySearch(int[] arr, int k, int lo, int hi) {

        if ( lo > hi)
            return;

        int m = (lo+hi)/2;

        if (arr[m] == k) {
            System.out.println("found");
            return;
        }
        if( arr[m] < k)
            binarySearch(arr, k, m+1, hi);
        else
            binarySearch(arr, k, lo, m-1);

        return;
    }
}
