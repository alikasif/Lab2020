package leetcode;

import java.util.PriorityQueue;

public class REmoveFromPile {

    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(-4);
        pq.add(-3);
        pq.add(-6);
        pq.add(-7);
        int k = 3;
        while ( k >0) {
            int e  = -1 * pq.poll();
            System.out.println(e);
            e = e - Double.valueOf(Math.floor(e/2)).intValue();
            pq.add(-1*e);
            k--;
        }

        int s= 0;
        while (!pq.isEmpty()) {
            s=s+(-1*pq.poll());
        }
        System.out.println(s);
    }
}
