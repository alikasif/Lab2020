package google2022;

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {

        int[] arr = {9,9,9,9};

        int r = -1;
        int d = -1;
        for(int i = arr.length-1; i>=0; i--) {

            if(i == arr.length-1)
                arr[i] =  arr[i]+1;
            else
                arr[i] = arr[i] + d;

            if( i == 0)
                continue;
            r = arr[i]%10;
            d = arr[i]/10;

            if(d ==0)
                break;
            else {
                arr[i] = r;
            }
        }
        System.out.println(Arrays.toString(arr));
        if(arr[0] > 9) {
            int[] arr2 = new int[arr.length+1];
            for( int i = arr.length-1; i>=2;i--) {
                arr2[i] = arr[i];
            }
            arr2[1] = 0;
            arr2[0] = 1;
            System.out.println(Arrays.toString(arr2));
        }
    }
}
