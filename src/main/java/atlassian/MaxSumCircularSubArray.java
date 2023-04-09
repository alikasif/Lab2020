package atlassian;
public class MaxSumCircularSubArray {
    public static void main(String[] args) {

        int cs1 = 0;
        int cs2 = 0;
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int ts = 0;

        int [] arr = {5, -2, 3, 4, -6, 5};

        for(int i=0; i<arr.length; i++) {
            System.out.println(cs1 +" "+cs2);
            cs1 = cs1 + arr[i];
            cs2 = cs2 + arr[i];
            ts = ts + arr[i];

            maxSum = Math.max(cs1, maxSum);

            if(cs1 <0)
                cs1 =0;

            minSum = Math.min(cs2, minSum);

            if(cs2 >0)
                cs2 =0;
        }
        System.out.println(ts +" "+ maxSum +" "+minSum);
        System.out.println(ts == minSum? maxSum : Math.max(ts-minSum, maxSum));
    }
}