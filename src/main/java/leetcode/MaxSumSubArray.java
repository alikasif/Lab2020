package leetcode;

public class MaxSumSubArray {

    public static void main(String[] args) {

        int[] arr = {1,2,5,-7,2,3};
 //       int[] arr = {10,-1,200,3,-4,100};

        int s = 0;
        int ms = 0;
        for(int i= 0;i<arr.length;i++) {

            if(arr[i] >=0 ) {
                s=s+arr[i];
            }
            else {
                ms = Math.max(ms, s);
                s=0;
            }
        }
        System.out.println(Math.max(s, ms));
    }
}
