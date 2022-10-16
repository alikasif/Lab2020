package google;

import java.util.Arrays;

public class DeleteDupes {

    public static void main(String[] args) {

        int[] arr = {2,3,5,5,7,11,11,11,13};

        int i = 1;
        int j = 1;

        while (i < arr.length) {
            System.out.println(Arrays.toString(arr)+" i: "+i +" j: "+j);
            if( arr[j-1] != arr[i] ) {
                arr[j] = arr[i];
                j++;
            }
            i++;
        }
        System.out.println(Arrays.toString(arr));

        while (j < arr.length)
            arr[j++] = 0;

        System.out.println(Arrays.toString(arr));
    }
}