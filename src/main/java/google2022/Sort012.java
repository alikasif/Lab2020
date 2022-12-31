package google2022;

import java.util.Arrays;

public class Sort012 {
    public static void main(String[] args) {

        int[] arr = {2,0,2,1,1,0};

        int l = 0;
        int m = 0;
        int h = arr.length-1;

        while (m<=h) {
            System.out.println(Arrays.toString(arr));
            if (arr[m] == 0) {
                int x = arr[m];
                arr[m] = arr[l];
                arr[l] = x;
                l++;
                m++;
            }
            else if (arr[m] == 1)
                m++;
            else {
                int x = arr[h];
                arr[h] = arr[m];
                arr[m] = x;
                h--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
