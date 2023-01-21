package google2022;

public class MaxSubArray {
    public static void main(String[] args) {

        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};

        int ms = Integer.MIN_VALUE;
        int cs = arr[0];

        for(int i=1; i<arr.length; i++) {
            if(cs+arr[i] < arr[i])
                cs = arr[i];
            else
                cs = cs + arr[i];

            ms = Math.max(ms, cs);
        }
        System.out.println(ms);
    }
}
