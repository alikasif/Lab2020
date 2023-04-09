package atlassian;

import java.util.Arrays;

public class ReduceCashflow {
    public static void main(String[] args) {

        int[][] arr = {
                {0,10,10,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,10},
                {0,0,0,0,0,30},
                {0,0,0,0,0,0}
        };

        int [] credit = new int[arr.length];

        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                if ( i != j) {
                    credit[i] = credit[i] - arr[i][j];
                    credit[j] = credit[j] + arr[i][j];
                }
            }
        }
        System.out.println(Arrays.toString(credit));
    }
}
