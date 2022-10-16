package leetcode;

import java.util.Arrays;

public class Three3Sum {

    public static void main(String[] args) {

        int[] arr = {-1, 0, 1, 2, -1, -4};

        // -4, -1, -1, 0, 1,  2

        Arrays.sort(arr);

        for(int i = 0; i< arr.length; i++) {

            int j = i+1;
            int k = arr.length-1;

            while ( j < k) {
                int s = arr[i] + arr[j] + arr[k];

                if (s == 0) {
                    System.out.println(arr[i] + " " + arr[j] + " " + arr[k]);
                    j++;
                    k--;
                }

                if (s > 0) {
                    k--;
                }
                if (s < 0) {
                    j++;
                }
            }
        }

        System.out.println("closest sum " + 3);
        int [] arr2 =  {-1,2,1,-4};
        closestSum(arr2, 1);
    }

    static void closestSum(int[] arr, int tgt) {

        Arrays.sort(arr);
        int distance = Integer.MAX_VALUE;

        for(int i = 0; i< arr.length; i++) {

            int j = i+1;
            int k = arr.length-1;

            while ( j < k) {
                int s = arr[i] + arr[j] + arr[k];
                int nd = Math.min(Math.abs(s-tgt), distance);

                if ( nd < distance) {
                    System.out.println(arr[i] + " " + arr[j] + " " + arr[k]);
                    j++;
                    k--;
                    distance = nd;
                }

                if (s > tgt) {
                    k--;
                }
                if (s < tgt) {
                    j++;
                }
            }
        }
    }
}
