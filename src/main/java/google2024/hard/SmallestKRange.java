package google2024.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

class Clazz2 {
    Integer val;
    Integer index;
    Integer arrindex;

    public Clazz2(Integer val, Integer index, Integer arrindex) {
        this.val = val;
        this.index = index;
        this.arrindex = arrindex;
    }

    @Override
    public String toString() {
        return "Clazz2{" +
                "val=" + val +
                ", index=" + index +
                '}';
    }
}
public class SmallestKRange {
    public static void main(String[] args) {

        int[][] arr = {
                {4,10,15,24,26},
                {0,9,12,20},
                {5,18,22,30}
        };

//        int[][] arr = {
//                {1,2,3},
//                {1,2,3},
//                {1,2,3}
//        };

        int k = arr.length;
        PriorityQueue<Clazz2> maxHeap = new PriorityQueue<>(k, new Comparator<Clazz2>() {
            @Override
            public int compare(Clazz2 o1, Clazz2 o2) {
                return o2.val.compareTo(o1.val);
            }
        });
        PriorityQueue<Clazz2> minHeap = new PriorityQueue<>(k, new Comparator<Clazz2>() {
            @Override
            public int compare(Clazz2 o1, Clazz2 o2) {
                return o1.val.compareTo(o2.val);
            }
        });
        Clazz2 c0 = new Clazz2(arr[0][0], 0, 0);
        Clazz2 c1 = new Clazz2(arr[1][0], 0,1);
        Clazz2 c2 = new Clazz2(arr[2][0], 0, 2);
        maxHeap.offer(c0); maxHeap.offer(c1); maxHeap.offer(c2);
        minHeap.offer(c0); minHeap.offer(c1); minHeap.offer(c2);

        System.out.println(maxHeap.peek());
        System.out.println(minHeap.peek());

        int smallest = minHeap.peek().val;
        int largest = maxHeap.peek().val;

        while (!minHeap.isEmpty()) {
            System.out.println(smallest +"|" + largest);
            Clazz2 poll = minHeap.poll();
            if ( (largest-smallest) >  (largest - poll.val) ) {
                    smallest = poll.val;
                    largest = maxHeap.peek().val;
            }
            maxHeap.remove(poll);

            if (arr[poll.arrindex].length-1 == poll.index)
                break;

            Clazz2 tmp = new Clazz2(arr[poll.arrindex][poll.index+1], poll.index+1, poll.arrindex);
            minHeap.offer(tmp);
            maxHeap.offer(tmp);
        }

        System.out.println("Smallest :: " +smallest +" | "+ largest);
    }
}
