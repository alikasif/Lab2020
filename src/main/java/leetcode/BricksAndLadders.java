package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class BricksAndLadders {

    public static void main(String[] args) {

/*
        int[] arr = {4, 12, 2, 7, 3, 18, 20, 3, 19};
        int bricks= 10;
        int ladders = 2;
*/

/*
        int[] arr = {1, 5, 1, 2, 3, 4, 10000};
        // 4 -4, 1, 1 1 99999
        int bricks= 4;
        int ladders = 1;
*/

        int[] arr = {4,12,2,7,3,18,20,3,19};
        int bricks= 10;
        int ladders = 2;

        Queue<Integer> minHeap = new PriorityQueue<>();

        int i = 0;
        int diff  = 0;

        while ( i < arr.length-1 ) {

            diff = arr[i+1] - arr[i];

            if ( diff > 0) {

                minHeap.add(diff);

                // check at this point if we shud use ladder or use brick
                if (minHeap.size() > ladders) {
                    bricks -= minHeap.poll();
                    if (bricks < 0)
                        break;
                }
            }
            i++;
        }
        System.out.println(i +" bricks: "+bricks);
    }
}