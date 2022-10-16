package leetcode;

import java.util.Arrays;
import java.util.Random;

public class KthLargestElement {

    static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    public static void main(String[] args) {

        int[] arr = {2,1,4,5,3};
        int kth = findKth(arr, 2);
        System.out.println(Arrays.toString(arr)+" "+kth);
    }
    static int findKth(int[] arr, int k) {
        int l = 0;
        int r = arr.length-1;
        Random rd = new Random(0);
        while ( l <= r) {
            int pi = rd.nextInt(r-l+1)+l;
            int npi = partition(arr, l, r, pi);
            System.out.println(l+" "+r+" "+pi+" "+npi);
            if( npi == k-1 ) {
                return arr[npi];
            }
            else if (npi > k-1) {
                r = npi-1;
            }
            else {
                l = npi+1;
            }
        }
        return -1;
    }
    static int partition(int[] arr, int l, int r, int pi) {
        // 3 2 1 5 4
        int pv = arr[pi];
        int npi = l;

        swap(arr, pi, r);
        for(int i=l; i<r; i++) {
            if(arr[i] > pv) {
                swap(arr, i, npi++);
            }
        }
        swap(arr, r, npi);
        //System.out.println(npi);
        return npi;
    }
}