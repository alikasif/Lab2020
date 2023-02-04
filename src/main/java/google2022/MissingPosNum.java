package google2022;

import java.util.Arrays;

public class MissingPosNum {
    public static void main(String[] args) {
        // int[] arr ={1,3,0,4};
        int[] arr ={7,6,0,9};

        for(int i=0; i<arr.length; i++) {
            System.out.println(Arrays.toString(arr));
            int v = arr[i];
            if(v > 0 && v < arr.length) {
                if(arr[v-1] != v){
                    Swap(arr, v-1, i);
                    i--;
                }
            }
        }
    }

    private static void Swap(int[] arr, int i, int i1) {
        int t = arr[i];
        arr[i]= arr[i1];
        arr[i1] = t;
    }
}
