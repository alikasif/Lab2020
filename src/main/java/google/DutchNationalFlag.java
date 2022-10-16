package google;

import java.util.Arrays;

public class DutchNationalFlag {

    public static void main(String[] args) {

        int[] arr ={1,2,0,2,1,0,1,2,0,1};
        m2(arr);
        System.out.println("m1");
        int i = 0;
        int j = 0;

        System.out.println(Arrays.toString(arr));
        while ( j < arr.length) {
            if(arr[j] == 0) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;
            }
            j++;
        }

        System.out.println(Arrays.toString(arr));
        j = i;
        while ( j < arr.length) {
            if(arr[j] == 1) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i++;
            }
            j++;
        }
        System.out.println(Arrays.toString(arr));
    }

    static void m2(int[] arr) {
        System.out.println(Arrays.toString(arr));
        int i =0;
        int j = arr.length-1;
        int k =1;

        while ( i < j && k < arr.length &&  k<=j) {

            if(arr[k] == 0) {
                int t = arr[i];
                arr[i] = arr[k];
                arr[k] = t;
                i++;
            }
            if(arr[k] == 2 ) {
                int t= arr[j];
                arr[j]= arr[k];
                arr[k] = t;
                j--;
            }
            if(arr[k] == 1)
                k++;
            System.out.println(Arrays.toString(arr) +" "+i+" "+j+" "+k);
        }
        System.out.println(Arrays.toString(arr));
    }
}