package google;

import java.util.Arrays;

class LIS {

    public static int lengthOfLIS(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;

        for(int i = 0; i<n; i++){
            int max = 0;
            for(int j = 0; j<i; j++){

                if(nums[j] < nums[i]){

                    if(dp[j] > max)
                        max= dp[j];
                }
                System.out.println(i+" "+j+" "+Arrays.toString(dp));
            }
            dp[i] = max+1;

            if(dp[i]>ans)
                ans = dp[i];
        }
        return ans;
    }
    // driver program to test above functions
    public static void main(String args[])
    {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int n = arr.length;
        int l = lengthOfLIS(arr);
        System.out.println(l);
    }
}
/*This code is contributed by Rajat Mishra*/
