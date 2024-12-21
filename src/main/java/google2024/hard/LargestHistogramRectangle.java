package google2024.hard;

import java.util.Arrays;

public class LargestHistogramRectangle {

    public static int largestRectangleArea(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        lessFromLeft[0] = -1;

        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;

        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        System.out.println(Arrays.toString(lessFromLeft));

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        System.out.println(Arrays.toString(lessFromRight));

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,5,6,2,3};
        System.out.println(Arrays.toString(arr));
        largestRectangleArea(arr);
    }
}
