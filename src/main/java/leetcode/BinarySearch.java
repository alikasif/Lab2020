package leetcode;

import java.util.Arrays;

public class BinarySearch {

    static int iterativeBS(int[] arr, int k){
        int l = 0;
        int h = arr.length-1;

        while (l <= h) {
            int m = l/2 + h/2;
            if(arr[m] == k)
                return m;
            else if (k > arr[m])
                l = m+1;
            else
                h = m-1;
        }
        return -1;
    }

    static int recursiveBS(int[] arr, int l, int h, int k) {
        //System.out.println(l +" "+h);

        if(l > h)
            return -1;

        int m = (l/2 + h/2);

        if(arr[m] == k) {
            return m;
        }
        else if ( k > arr[m]){
            return recursiveBS(arr, m+1, h, k);
        }
        else {
            return recursiveBS(arr, l, m-1, k);
        }
    }

    static int findAOfIEqualsI(int [] arr) {

        int l = 0;
        int h = arr.length-1;
        while (l <= h) {
            int m = l/2 + h/2;
            if (arr[m] -m == 0) {
                return m;
            }
            else if (arr[m] -m > 0) {
                h = m-1;
            }
            else
                l = m+1;
        }
        return -1;
    }

    static int searchMinInCyclic(int[] arr) {

        // 1 2 3 4 5 6 7 8
        // 7 8 1 2 3 4 5 6
        // pivot point will have bigger/smaller element on both left & right

        int l = 0;
        int h = arr.length-1;

        while ( l < h ) {
            int m = (l/2 + h/2);
            System.out.println(l+" "+m+" "+h);

            if( arr[m] < arr[h] ) {
                h = m;
            }
            else  {
                l = m+1;
            }
        }
        return h;
    }

    static int findPivot(int[] arr) {

        // 1 2 3 4 5 6 7 8
        // 4 5 6 7 8 1 2 3
        // 8 1 2 3 4 5 6 7
        // pivot point will have bigger/smaller element on both left & right

        int l = 0;
        int h = arr.length-1;
        int p = -1;
        while ( l < h ) {

            int m = l + (h-l)/2;
            System.out.println(l+" "+m+" "+h);

            if(arr[m] > arr[m+1] && arr[m] > arr[m-1]) {
                p = m;
                break;
            }
            else if (arr[m] < arr[m+1] && arr[m] < arr[m-1]) {
                p=m;
                break;
            }
            else {
                    if (arr[l] > arr[m])
                        h = m-1;
                    else
                        l = h+1;
            }
        }
        return p;
    }

    static int findInCyclic(int[] arr, int k) {

        // 4 5 6 7 8 1 2 3
        // 8 1 2 3 4 5 6 7

        int p = findPivot(arr);
        System.out.println("pivot "+p);
        if (arr[p] == k)
            return p;
        int i = iterativeBS(Arrays.copyOfRange(arr, 0, p), k);
        if (i == -1)
            i = iterativeBS(Arrays.copyOfRange(arr, p+1, arr.length), k);
        return i;
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7};
    /*
        System.out.println(recursiveBS(arr,0, arr.length-1, 3));
        System.out.println(iterativeBS(arr, 3));
        System.out.println(findAOfIEqualsI(new int[] {-2,0,2,4,6,7,9}));
        System.out.println(searchMinInCyclic(new int[] {1,2,3,4,5,6,7,8}));*/
        //System.out.println(findPivot(new int[] {5, 6, 7, 8, 1,2,3,4}));
        //System.out.println(findPivot(new int[] {8,1,2,3,4,5,6,7}));
        //System.out.println(findInCyclic(new int[] {8,1,2,3,4,5,6,7}, 8));

        int hd = 9 * 30;
        int md = 60 * 6;
        int ad = (md/12);
        int absd = Math.abs(md-hd);
        if ( hd > md)
            System.out.println(absd+ad);
        else
            System.out.println(absd - ad);

    }
}
