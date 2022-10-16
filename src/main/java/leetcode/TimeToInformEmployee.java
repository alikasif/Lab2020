package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeToInformEmployee {

    public static void main(String[] args) {

        // n = 6
        // Emp 0 1 2 3 4 5
        // 2 2 -1 2 1 2
        // 0 0 1 0 1 0

        /*

                            2
                    0       3       4
                1               5       6
                                    7

         */

//        int n = 8;
//        int[] mngr = {2, 0, -1, 2, 2, 4, 4, 5};
//        int[] time = {2, 0, 3, 0, 5, 2, 0, 1};

//        int n = 6;
//        int[] mngr = {2, 2, -1, 2, 2, 2};
//        int[] time = {0,0,1,0,0,0};

        int n = 15;
        int[] mngr = {-1,0,0,1,1,2,2,3,3,4,4,5,5,6,6};
        int[] time = {1,1,1,1,1,1,1,0,0,0,0,0,0,0,0};

/*
                                            0
                                1                         2
                        3           4               5              6
                    7       8     9   10          11   12      13     14
 */

        int ttime = 0;
        List<Integer> queue = new ArrayList<>();
        queue.add(0);
        boolean hasElements= true;
        while (hasElements) {

            List<Integer> tmpqueue = new ArrayList<>();
            int t=0;
            while (!queue.isEmpty()) {
                int m = queue.get(0);
                queue.remove(0);

                for (int i = 0; i < mngr.length; i++) {
                    if (mngr[i] == m) {
                        t = Math.max(t, time[m]);
                        tmpqueue.add(i);
                    }
                }
            }

            if(tmpqueue.isEmpty()) {
                hasElements = false;
            }
            else {
                ttime = ttime+t;
                queue.addAll(tmpqueue);
            }

        }
        System.out.println(ttime);
    }
}