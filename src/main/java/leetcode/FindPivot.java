package leetcode;

import java.util.Arrays;

public class FindPivot {

    public static void main(String[] args) {

        //int[] arr = {2,4,5,6,7,0,1,1,2};
        int[] arr = {2,2,2,0,0,0,1,1};
        System.out.println(findPivot(arr));
    }

    static int findPivot(int[] arr) {

        System.out.println(Arrays.toString(arr));

        if(arr[0] < arr[arr.length-1])
            return -1;

        int m = arr.length/2;

        if(arr[m] >= arr[m-1] && arr[m] >= arr[m+1]) {
            return m;
        }
        else if (arr[m] <= arr[m-1] && arr[m] <= arr[m+1])
            return m;
        else {
            int p = findPivot(Arrays.copyOfRange(arr, 0, m));
            if (p != -1)
                return p;

            int p1= findPivot(Arrays.copyOfRange(arr, m, arr.length));
            if ( p1 != -1)
                return p1;
        }
        return -1;
    }
}
