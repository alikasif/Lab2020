package salesforce;

import java.util.Arrays;

public class MaxLenRepeatedSubArray {
    public static void main(String[] args) {

        int[] nums1 = {1,2,3,2,1};

        int[] nums2 = {3,2,1,4,7};

        int i = 0;
        int j = 0;
        int k = -1;

        int maxLen = Integer.MIN_VALUE;

        while (i<nums1.length && j< nums2.length) {

            if(nums1[i] == nums2[j]) {
                if (k == -1)
                    k = i;
                i++;
                j++;
                System.out.println(Arrays.toString(Arrays.copyOfRange(nums1, k, i)));
                maxLen = Math.max(maxLen, (i-k));
            }
            else {
                if(k !=-1)
                    i = k;
                else
                    i++;
                k=-1;
            }
        }
        System.out.println(maxLen);
    }
}
