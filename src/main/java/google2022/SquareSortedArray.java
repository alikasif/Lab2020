package google2022;

import java.util.Arrays;

public class SquareSortedArray {

    public static void main(String[] args) {
        int[] arr = {-4,-1,0,3,10};
        sq2(arr);
        sq2(new int[] {-7,-3,2,3,11});
    }

    static void sq2(int[] arr) {

        int i=0;
        int j = arr.length-1;
        while ( i <= j) {
            System.out.println(Arrays.toString(arr));

            if( Math.abs(arr[i]) > Math.abs(arr[j]) ) {
              int t = arr[j];
              arr[j] = arr[i] * arr[i];
              arr[i] = t;
              j--;
            }
            else {
                arr[j] = arr[j] * arr[j];
                j--;
            }

        }
        System.out.println(Arrays.toString(arr));


    }
}
