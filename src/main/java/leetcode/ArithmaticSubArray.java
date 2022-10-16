package leetcode;

import java.util.Arrays;

public class ArithmaticSubArray {

    public static void main(String[] args) {

        int [] nums = {4,6,5,9,3,7};
        int[] l = {0,0,2};
        int[] r = {2,3,5};

        for(int i = 0; i< l.length; i++) {

            int[] range = Arrays.copyOfRange(nums, l[i], r[i]+1);
            System.out.println(Arrays.toString(range));
            Arrays.sort(range);
            int diff = range[1] - range[0];
            for(int k = 1; k < range.length; k++) {
                if(range[k] - range[k-1] != diff)
                    return;
            }
            System.out.println("found "+ Arrays.toString(range));

        }

    }
}
