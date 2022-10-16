package google2022;

import java.util.Arrays;

public class FindFirstLastPos {

    public static void main(String[] args) {

        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int[] range = {100, -1};
        findX(nums, 0, nums.length-1, target, range);
        System.out.println(Arrays.toString(range));
    }

    static void findX(int[] nums, int l, int h, int t, int[] range) {
        if (l > h)
            return;

        int m = (l+h)/2;

        if (nums[m] == t) {

            if(range[0] > m) {
                range[0] = m;
                findX(nums, l, m-1, t, range);
            }

            if (range[1] < m) {
                range[1] = m;
                findX(nums, m+1, h, t, range);
            }
        }

        if(nums[m] > t) {
            findX(nums, l, m-1, t, range);
        }
        else {
            findX(nums, m + 1, h, t, range);
        }
    }
}
