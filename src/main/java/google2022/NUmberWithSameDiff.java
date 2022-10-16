package google2022;

import java.util.Arrays;

public class NUmberWithSameDiff {

    public static void main(String[] args) {

        int n=4,k=7;
        int x=1;
        while (x <10) {

            int [] arr = new int[n];
            for(int i=0; i<n;i++) {
                arr[i] = x;
            }
            int v=0;
            if ( x>=k) {
                v = x-k;
            }
            else {
                v = x + k;
            }
            if( v > 9 || v <0) {
                x++;
                continue;
            }

            for(int i=1;i<(1+(n/2));i++) {
                arr[i] = v;
            }
            System.out.println(Arrays.toString(arr));
            x++;
        }
    }
}
