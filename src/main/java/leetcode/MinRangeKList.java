package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinRangeKList {

    public static void main(String[] args) {

        int [] arr1 = {4, 9, 11, 12, 15};
        int [] arr2 = {0, 8, 10, 14, 20};
        int [] arr3 = {6, 12, 16, 30, 50};

        int l = -1;
        int r = -1;

        Queue<Pair> pq = new PriorityQueue();
        int i = 0;
        int j = 0;
        int k = 0;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int minrange = Integer.MAX_VALUE;

        while(i < arr1.length && j < arr2.length && k < arr3.length) {

            if(pq.isEmpty()) {
                pq.add(new Pair(arr1[i], 0));
                pq.add(new Pair(arr2[j], 1));
                pq.add(new Pair(arr3[k], 2));
                minrange = Integer.MAX_VALUE;
                max = Math.max(arr1[i], Math.max(arr2[j], arr3[k]));
                i++; j++; k++;
                continue;
            }
            else {
                Pair poll = pq.poll();
                min = poll.v;
                System.out.println(poll + " "+ minrange + " "+min +" "+max);

                if( (max-min) < minrange) {
                    l = min;
                    r= max;
                }

                minrange = Math.min(minrange, (max-min));

                if(poll.i == 0 && i < arr1.length) {
                    pq.add(new Pair(arr1[i], 0));
                    max = Math.max(max, arr1[i]);
                    i++;
                }

                if(poll.i == 1 && j < arr2.length) {
                    pq.add(new Pair(arr2[j], 1));
                    max = Math.max(max, arr2[j]);
                    j++;
                }

                if(poll.i == 2 && k < arr3.length) {
                    pq.add(new Pair(arr3[k], 2));
                    max = Math.max(max, arr3[k]);
                    k++;
                }
            }
        }

        System.out.println(min +" "+max);
        System.out.println(l+" "+r);
    }
}