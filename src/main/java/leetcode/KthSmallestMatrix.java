package leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

class E implements Comparable<E>{
    int v;
    int r;
    int c;

    public  E(int v, int r, int c){
        this.v = v;
        this.r =r;
        this.c =c;
    }

    @Override
    public String toString() {
        return "E{" +
                "v=" + v +
                ", r=" + r +
                ", c=" + c +
                '}';
    }

    @Override
    public int compareTo(E o) {
        if (o.v < this.v)
            return 1;
        else if ( o.v > this.v)
            return -1;
        else
            return 0;
    }
}
public class KthSmallestMatrix {

    public static void main(String[] args) {

        int[][] arr = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };

        int  k = 8;

        Queue<E> minHeap = new PriorityQueue<>();

        for(int i= 0; i < arr[0].length; i++) {
                minHeap.add(new E(arr[0][i], 0, i));
        }

        int i = 0;
        E e = null;
        while (i++ < k) {
            e = minHeap.remove();
            System.out.println(e);
            if (e.r+1 < arr[0].length)
                minHeap.add(new E(arr[e.r+1][e.c], e.r+1,e.c));
        }
        System.out.println("Kth smallest: "+e);
    }
}