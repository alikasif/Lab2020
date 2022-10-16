package google2022;

public class MaxSumSubArray {

    public static void main(String[] args) {

        //int[] arr= {-2,1,-3,4,-1,2,1,-5,4};
        //int[] arr= {5,4,-1,7,8};
        //int[] arr = {1};
        //int[] arr = {-1, -2};
        int[] arr = {-1, -1,-2,-2};
        //int[] arr = {1, -1,-2};

        int globalsum = Integer.MIN_VALUE;

        int sum = Integer.MIN_VALUE;
        sum = arr[0];
        int i=1;

        while ( i < arr.length ) {

            if(arr[i] > sum && arr[i]+sum < arr[i]) {
                sum = arr[i];
            }
            else {
                if (sum < 0 && arr[i] < 0 && sum >= arr[i])
                    ;
                else {
                    globalsum = Math.max(globalsum, sum);
                    sum = sum + arr[i];
                }
            }
            globalsum = Math.max(globalsum, sum);
            i++;
        }
        globalsum = Math.max(globalsum, sum);
        System.out.println(globalsum);
    }
}
