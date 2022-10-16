package leetcode;

import java.util.PriorityQueue;

class PQNode implements Comparable<PQNode>{

    int index;
    int val;

    public PQNode(int i, int v){
        this.index = i;
        this.val = v;
    }

    @Override
    public String toString() {
        return "PQNode{" +
                "index=" + index +
                ", val=" + val +
                '}';
    }

    @Override
    public int compareTo(PQNode o) {
        if (o.val < val)
            return 1;
        else if (o.val > val)
            return -1;
        else
            return 0;
    }
}

public class ClosestIntervalInSortedArrays {

    public static void main(String[] args) {

        int[] arr1 = {5, 10, 15};
        int[] arr2 = {3, 6, 9, 12, 15};
        int[] arr3 = {8, 16, 24};

        // 3 5 8
        //5 6 8
        // 6 8 10
        //8 9 10
        //9 10 16
        //10 12 16
        // 12 15 16
        // 15 15 16
        //

        PriorityQueue<PQNode> queue = new PriorityQueue<>(3);
        PriorityQueue<PQNode> maxqueue = new PriorityQueue<>(3);
        int i =0;
        int j =0;
        int k =0;

        queue.add(new PQNode(0, arr1[i]));
        queue.add(new PQNode(1, arr2[j]));
        queue.add(new PQNode(2, arr3[k]));

        maxqueue.add(new PQNode(0, -1*arr1[i]));
        maxqueue.add(new PQNode(1, -1*arr2[j]));
        maxqueue.add(new PQNode(2, -1*arr3[k]));

        int itr =0;
        boolean polled = true;
        while( polled && i < arr1.length && j < arr2.length && k < arr3.length ) {
            polled = false;
            PQNode poll = queue.poll();
            PQNode maxn = maxqueue.peek();

            if (poll == null)
                return;
            System.out.println((itr++) + " => "+ poll + " "+ maxn +" i = "+i+" j = "+j +" k = "+k);
            //System.out.println(-1*maxn.val + poll.val);

            if(poll.index == 0 && i < arr1.length-1) {
                i++;
                queue.offer(new PQNode(0, arr1[i]));
                maxqueue.offer(new PQNode(0, -1*arr1[i]));
                polled = true;
            }
            if(poll.index == 1 && j < arr2.length-1) {
                j++;
                queue.offer(new PQNode(1, arr2[j]));
                maxqueue.offer(new PQNode(1, -1*arr2[j]));
                polled = true;
            }
            if(poll.index == 2 && k < arr3.length-1) {
                k++;
                queue.offer(new PQNode(2, arr3[k]));
                maxqueue.offer(new PQNode(2, -1*arr3[k]));
                polled = true;
            }
        }
    }
}
