package leetcode;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MergeSort {

    public static void main(String[] args) {

        AtomicInteger count = new AtomicInteger(0);
//        int[] arr= {5, 2, 6, 9, 3};
        int[] arr = {-2, 5, -1};

        int n = arr.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            //System.out.println("i: "+i);
            sums[i + 1] = sums[i] + arr[i];
        }
        //sort(arr, 0, arr.length-1, count);
        sort(sums, 0, sums.length-1, count);
        System.out.println(Arrays.toString((sums)));

        System.out.println(count.intValue());
    }

    static void sort(long[] arr, int l, int h, AtomicInteger count) {
//        System.out.println(l+"  "+h);

        if (l < h) {

            int m = (l + h) / 2;
            sort(arr, l, m, count);
            sort(arr, m+1, h, count);
            merge(arr, l, h, m, count);
        }
    }

    static void merge(long[] arr, int l, int h, int m, AtomicInteger count) {

        long[] f = Arrays.copyOfRange(arr,l, m+1);
        long[] s = Arrays.copyOfRange(arr, m+1, h+1);

        System.out.println("l => "+l+" h => "+h+" m => "+m);
        System.out.println("f=> "+Arrays.toString(f));
        System.out.println("s=> "+Arrays.toString(s));

        int i = 0;
        int j = 0;
        int k = l;

        while(i < f.length && j < s.length) {
            if(f[i] <= s[j]) {
                arr[k] = f[i];
                i++;
            }
            else {
                int t= f.length-i;
                count.addAndGet(t);
                arr[k] = s[j];
                j++;
            }
            k++;
        }
        while(i < f.length) {
            arr[k] = f[i];
            k++;
            i++;
        }
        while ( j < s.length) {
            //count.incrementAndGet();
            arr[k] = s[j];
            k++;
            j++;
        }
        System.out.println(Arrays.toString(arr));
    }
}