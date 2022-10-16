package google;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class KClosestElements {

    public static void main(String[] args) {

        int[] arr = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};
        System.out.println(Arrays.toString(arr));
        int x = 4;
        int k = 16;

        final AtomicInteger j = new AtomicInteger(-1);
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
        }
    }
}