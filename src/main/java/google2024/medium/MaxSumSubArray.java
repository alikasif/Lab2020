package google2024.medium;

public class MaxSumSubArray {
    public static void main(String[] args) {

        //int[] arr = {-2,-1,-3,4,-1,2,1,-5,4};
        int[] arr = {5,4,-1,7,8};

        int maxSum = 0;
        int sum = arr[0];

        for(int i=1; i<arr.length; i++) {

            if (sum < 0 && arr[i] >= 0) {
                sum = arr[i];
            }
            else if (arr[i] < 0 && arr[i]> sum) {
                sum = arr[i];
            }
            else {
                sum = sum + arr[i];
            }
            maxSum = Math.max(sum, maxSum);
        }
        System.out.println(maxSum);
    }
}
