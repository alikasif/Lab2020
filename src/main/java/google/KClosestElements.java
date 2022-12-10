package google;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class KClosestElements {

    static int findKClosest(int[] arr, int k, int x) {
        int l = 0;
        int r = arr.length-1;

        while ( l < r ) {

            int m = (l+r)/2;
            System.out.println(l +" "+r +" "+m +" "+arr[m] );

                if (arr[m] < x) {
                    l = m+1;
                } else if (arr[m] > x) {
                    r = m-1;
                }
                else {
                    l = m;
                    break;
                }
            }
        System.out.println(l +" "+r);
        return l;
    }

    public static void main(String[] args) {

        int[] arr = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};

        System.out.println(Arrays.toString(arr));
        int k = 4;
        int x = 51;

        int kClosest = findKClosest(arr, k, x);
        int c = 0;

        int l = kClosest;
        int r = kClosest+1;

        while (l >0 && r < arr.length-1 && c < k) {
            if (Math.abs(x - arr[l]) < Math.abs(arr[r] - x))
                System.out.println(arr[l--]);
            else
                System.out.println(arr[r++]);
            c++;
        }

        while (c < k && l <0) {
            System.out.println(arr[r++]);
            c++;
        }

        while (c < k && r > arr.length) {
            System.out.println(arr[l--]);
            c++;
        }

/*        final AtomicInteger j = new AtomicInteger(-1);
        Arrays.stream(arr).sequential().forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                if(value <= k)
                    j.incrementAndGet();
            }
        });

        int i = Arrays.binarySearch(arr, k);
        System.out.println(i);
        System.out.println(j.intValue());
        int p = j.intValue()-1;
        int n = j.intValue()+1;

        while (x >0 && p>=0 && n < arr.length) {
            if( p >=0 && n < arr.length && (Math.abs(k - arr[p]) < Math.abs(k - arr[n])) ) {
                System.out.println(arr[p]);
                p--;
                x--;
            }
            if( p >=0 && n < arr.length && (Math.abs(k - arr[p]) > Math.abs(k - arr[n])) ) {
                System.out.println(arr[n]);
                n++;
                x--;
            }
        }
        while ( x > 0 && p>=0 ) {
            System.out.println(arr[p]);
            p--;
            x--;
        }
        while ( x > 0 && n < arr.length ) {
            System.out.println(arr[n]);
            n++;
            x--;
        }*/
    }
}