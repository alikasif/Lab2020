package leetcode;

public class MaxNonAdjacentSum {

    public static void main(String[] args) {

        int[] arr ={1, 0, 3, 9, 12, 5};
        // {1, 0, 3, 9, 2, 5}  1 9 5
        //{1, 0, 3, 9, 12, 5}  1 3 12

        int i = 1;
        int maxsum= Integer.MIN_VALUE;

        int sum = arr[0];
        int tmp = 0;

        while( i < arr.length ) {
            int t = Math.max(tmp, sum);
            sum = tmp + arr[i];
            tmp = t;
            i++;
        }
        System.out.println(tmp +" "+sum);
    }
}