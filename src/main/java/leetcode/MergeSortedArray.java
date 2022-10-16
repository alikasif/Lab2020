package leetcode;

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {

        int[] arr1 = {5,13,17,-1,-1,-1,-1,-1};
        int[] arr2 = {3,7,11,19};

        int i = 2;
        int j = arr2.length-1;
        int k = 6;

        while ( i >=0 && j>=0) {

            if (arr1[i] > arr2[j]){
                arr1[k] = arr1[i];
                k--;
                i--;
            }
            else if (arr2[j] > arr1[i]) {
                arr1[k] = arr2[j];
                k--;
                j--;
            }
        }
        while ( i >=0) {
            arr1[k] = arr1[i];
            k--;
            i--;
        }
        while ( j >=0) {
            arr1[k] = arr2[j];
            k--;
            j--;
        }
        System.out.println(Arrays.toString(arr1));
    }
}
