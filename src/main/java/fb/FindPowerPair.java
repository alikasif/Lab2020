package fb;

import java.util.Arrays;

public class FindPowerPair {

    public static void main(String[] args) {

        //int [] arr = {2, 1, 6};
        //int [] arr2 = {1, 5, 3};

        int [] arr = {2,3,4,5};
        int[] arr2={1,2,3};

        Arrays.sort(arr2); // nlogn
        // 1 2 3 5

        System.out.println(Arrays.toString(arr2));

        int l = 0;
        int h  = arr2.length-1;

        for (int x : arr) {
            int y = binarySearch(arr2, l, h, x);
            System.out.println(x + " ==> " + y);
        }
    }

    static int binarySearch(int[] arr, int l, int h, int e) {

        if ( l > h ) {
            if( arr[h] <= e) {
                return h;
            }
            return -1;
        }

        int m = (l+h)/2;

        if (arr[m] == e) {
            return m;
        }

        if ( arr[m] > e ) {
            if( arr[m-1] < e ) {
                return m-1;
            }
            else {
                return binarySearch(arr, l, m-1, e);
            }
        }
        else {
            if( m+1 < arr.length && arr[m+1] > e ) {
                return m;
            }
            return binarySearch(arr, m+1, h, e);
        }
    }
}
