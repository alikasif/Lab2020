package leetcode;

import java.util.concurrent.atomic.AtomicInteger;

public class PeakElement {

    public static void main(String[] args) {

        //int[] arr = {1, 0, 1, 3, 5, 6, 4};
        int[] arr ={1,2,3,1};
        AtomicInteger c = new AtomicInteger(0);
        findPeak(arr, 0, arr.length-1, c);
    }

    static void findPeak(int[] arr, int l, int h, AtomicInteger c) {
        System.out.println('1');
        if (c.get() > 0 || l > h)
            return;

        int m = (l+h)/2;

        if ( m-1 >= 0 && arr[m] > arr[m-1] && m+1 < arr.length && arr[m] > arr[m+1]) {
            System.out.println(m);
            c.incrementAndGet();
            return;
        }
        findPeak(arr, m+1, h, c);
        findPeak(arr, l, m-1, c);
    }
}
