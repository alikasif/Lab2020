package google2022;

import java.util.Arrays;

public class SearchInRotatedArray {

    public static void main(String[] args) {
        // int[] nums = {0,1,2,3,4,5,6,7};
         // int[] nums = { 7,6,5,4,3,2,1,0 };
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        //int[] nums = {4,5,6,7,0};
        //int[] nums = {1,1,1,1,1};
        int k = 1;
        int pivot = findPivot(nums, 0, nums.length-1);

        if ( nums[pivot] == k )
            System.out.println(pivot);

        else if( nums[0] < k && nums[pivot-1] > k ) {
            int[] tmp = Arrays.copyOfRange(nums,0, pivot);
            System.out.println(findK(nums,0, pivot-1, k));
        }
        else {
            int[] tmp = Arrays.copyOfRange(nums,pivot+1, nums.length);
            System.out.println(findK(nums, pivot+1, nums.length-1, k));
        }
    }

    static int findK(int[] nums, int l, int h, int k) {
        System.out.println(Arrays.toString(nums) +" l="+l +" h="+h);
        if ( nums.length==0 || l >= h)
            return -1;

        int m = (l+h)/2;

        if( nums[m] == k)
            return m;
        if(k < nums[m])
            return findK(nums, l, m-1, k);
        else
            return findK(nums, m+1, h, k);
    }

    static int findPivot(int[] nums, int l, int h) {

        System.out.println(Arrays.toString(nums) +" l="+l +" h="+h);

        if(nums.length == 0 || (l==h) || h == -1 || h < l)
            return -1;

        int m = (l+h)/2;

        System.out.println(Arrays.toString(nums) +" l="+l +" h="+h +" m="+m);

        if(m > 0 && nums[m] < nums[m-1] && m < nums.length-1 && nums[m] < nums[m+1])
            return m;

        if(m > 0 && nums[m] > nums[m-1] && m < nums.length-1 && nums[m] > nums[m+1])
            return m;

        int k = findPivot(nums, l, m-1);
        if( k != -1)
            return k;
        else {
            k = findPivot(nums, m + 1, h);
            if (k !=-1)
                return k;
        }
        return -1;
    }
}