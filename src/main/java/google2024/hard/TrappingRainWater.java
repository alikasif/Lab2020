package google2024.hard;

import java.util.Arrays;

public class TrappingRainWater {

    public static void main(String[] args) {

        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        //int[] arr = {1,8,6,2,5,4,8,3,7};
        int trap = trap(arr);
        System.out.println(trap);

    }

    /*
        Consider each i as bin. We need to find out how much water that bin can hold.
        So we look at the wall at each side to create a bin.
        so the size of bin is min of the height on both side. and this is the water it can hold.
        But the bin itself has certain bottom height which we need to reduce.
        in this way we sum up all the bin size to get max water.
     */
    static public int trap(int[] height) {

        int n = height.length;
        if (n == 0) return 0;

        int[] left = new int[n];
        int[] right = new int[n];
        int storedWater = 0;

        // Fill left array
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        // Fill right array
        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        // Calculate trapped water
        for (int i = 0; i < n; i++) {
            int minHeight = Math.min(left[i], right[i]);
            storedWater += minHeight - height[i];
            System.out.print(i+"|"+storedWater +" => ");
        }

        return storedWater;

    }
}
