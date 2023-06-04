package salesforce;

import java.util.ArrayList;
import java.util.List;

public class LongestSubArrayOf1 {

    public static void main(String[] args) {

        //int[] arr = {0, 1, 1, 1, 0, 1, 1, 0, 1};
        int[] arr = {1, 1, 0, 1};

        int zc = -1;
        int zi = -1;

        for(int i=0; i<arr.length; i++) {

            if(arr[i] == 1)
                continue;

            else if(arr[i] == 0) {

                if(zc == -1) {
                    zc = 1;
                    zi = i;
                }

                else {
                    System.out.println(i-zi-1);
                    while (arr[zi] ==0)
                        zi++;
                }
            }
        }
        System.out.println(arr.length-zi-1);
    }
}