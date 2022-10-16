package google2022;

import java.util.Arrays;

public class RemoveDupesFromSortedArray {

    public static void main(String[] args) {

        //int[] arr = {0,0,1,1,1,2,2,3,3,4};
        int[] arr = {1,1};

        int i = 0;
        int j = 1;

        while ( j < arr.length) {
            if(arr[i] == arr[j]) {
                j++;
            }
            else {
                i++;
                arr[i] = arr[j];
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(i);
        while ( ++i < arr.length)
            arr[i] = -1;
        System.out.println(Arrays.toString(arr));
    }
}
