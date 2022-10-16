package leetcode;

import java.util.Arrays;

public class NextPermutation {

    public static void main(String[] args) {

        //int [] arr = {5,3,4,9,7,6};
        int [] arr = {2,3,1};

        // 132 -> 231  -> 213
        // 123 132
        // 213 231
        // 231  -> 321 - > 312
        // 23510 -> 25310 -> 25301
        // 321 123
        // 312 321
        // 218765 258761 251678

        int i = arr.length-1;

        // find a bigger number from end
        while ( i > 0 && arr[i] < arr[i-1]) {
            i--;
        }

        int si = i-1;
        int sv = arr[si];
        System.out.println("si: "+si);

        int j = si+1;
        int l = arr[j];
        i = j;

        while ( j < arr.length) {
            if (arr[j] > sv && arr[j] < l) {
                i = j;
                l = arr[i];
            }
            j++;
        }
        System.out.println("i: "+i +" l: "+l);

        int t = arr[i];
        arr[i] = arr[si];
        arr[si] = t;

        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr,si+1, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
