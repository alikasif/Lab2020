package google2024.medium;

import java.util.Arrays;

public class JumpGame {

    public static void main(String[] args) {
        //int[] arr = {3, 2, 1, 0, 4};
        int[] arr = {2, 3, 1, 1, 4};
        System.out.println(Arrays.toString(arr));
        System.out.println(canJump(arr));
    }

    static boolean canJump(int[] nums) {

        int gas = 0;

        for (int n : nums) {
            System.out.println(gas +" | "+n);
            if (gas < 0)
                return false;
            else if ( n > gas)
                gas = n;
            gas -= 1;
        }
        System.out.println(gas);
        return true;
    }

    static boolean canJump(int[] A, int n) {

        int last = n-1;

        for(int i=n-2; i>=0; i--){
            System.out.println(last );
            if(i+A[i] >= last)
                last = i;
        }

        return last <= 0;
    }

    static void jump(int curr, int[] arr, int dest) {
        System.out.println(curr);

        int x = arr[curr];
        if ( x == dest )
            System.out.println("reached");
        while (x > 0) {
            if (curr + x == dest) {
                System.out.println("reached");
                return;
            }
            jump(x, arr, dest);
            x--;
        }
    }
}
