package leetcode;

import java.util.Arrays;

public class MatrixRotate {

    public static void main(String[] args) {

        int[][] arr = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };

        /*
                4 8 12 16
                3 7 11 15
                2 6 10 14
                1 5 9 13
         */

        int[][] arr90 = new int[4][4];

        int r =0;
        int c= 0;

        for(int i=0;i<4;i++) {
            for(int j=3;j>=0;j--) {
                arr90[r][c] = arr[i][j];
                r++;
            }
            c++;
            r=0;
        }

        for(int i =0; i<4;i++)
            System.out.println(Arrays.toString(arr90[i]));
    }
}
