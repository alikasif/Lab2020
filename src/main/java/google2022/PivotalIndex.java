package google2022;

import java.util.Arrays;

public class PivotalIndex {

    public static void main(String[] args) {

        int[] nums = {1,7,3,6,5,6};

        int[] lsum = new int[nums.length];
        int[] rsum = new int[nums.length];

        lsum[0] = nums[0];

        for(int i=1; i<nums.length; i++) {
            lsum[i] = nums[i] + lsum[i-1];
        }

        rsum[rsum.length-1] = nums[nums.length-1];
        for(int i=nums.length-2; i>=0; i--) {
            rsum[i] = nums[i] + rsum[i+1];
        }

        System.out.println(Arrays.toString(lsum));
        System.out.println(Arrays.toString(rsum));

        System.out.println(pivoti(nums));
    }

    static int pivoti(int[] nums) {
        int total = 0, sum = 0;

        for (int num : nums)
            total += num;

        for (int i = 0; i < nums.length; sum += nums[i++])
            if (sum * 2 == total - nums[i]) return i;
        return -1;
    }
}
