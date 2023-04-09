package atlassian;

import java.util.Map;

public class MaxProductOf3Numbers {

    public static void main(String[] args) {

        int [] arr = {-5,4,2,-7,1,9};

        // sort nlogn
        // -1, -2, 0, 1, 2, 2, 2, 2, 9

        int cp = arr[0];

        for( int i=1; i<arr.length; i++) {

            if(arr[i] > 0 && cp < 0) {
                cp = arr[i];
                continue;
            }
            else if (arr[i] > 0 && cp > 0) {
                cp = cp * arr[i];
                continue;
            }
            else if (arr[i] <0 && cp > 0){
                continue;
            }
            else {
                cp = cp * arr[i];
            }
        }
        System.out.println(cp);
        OrderNSolution(arr);
    }

    static void OrderNSolution(int[] arr) {
        // find smallest 2 and largest 3

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        min1 = arr[0];
        max1 = arr[0];

        for(int i=1; i<arr.length; i++) {

            if (arr[i] < min1) {
                min2 = min1;
                min1 = arr[i];
            }

            // {-5,4,2,-7,1,9};

            if(arr[i] > max1) {

                int t = max2;

                max2 = max1;
                max1 = arr[i];

                if( max3 < 0 || t > max3)
                    max3 = t;
            }
            else if (arr[i] > max2) {
                max3 = max2;
                max2 = arr[i];
            }
            else if (arr[i] > max3) {
                max3 = arr[i];
            }
        }
        System.out.println(min1 +" "+ min2);
        System.out.println(max1 +" "+ max2 +" "+ max3);
        System.out.println(Math.max( min1*min2*max1, max1*max2*max3));
    }
}
