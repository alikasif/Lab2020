package google2024.medium;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/93703/share-my-explained-greedy-solution/

public class BalloonBurst {
    public static void main(String[] args) {

        int[][] arr ={
                {10, 16},
                {2, 8},
                {1, 6},
                {7, 12}
        };

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        for(int[] x : arr)
            System.out.println(Arrays.toString(x));

        int arrowPos = arr[0][1];
        int arrowCnt = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i][0] <= arrowPos) {
                continue;
            }
            arrowCnt++;
            arrowPos = arr[i][1];
        }
        System.out.println(arrowCnt);
    }
}
