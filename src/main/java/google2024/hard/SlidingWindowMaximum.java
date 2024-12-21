package google2024.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class clazz1 implements Comparable<clazz1>{

    Integer val;
    int index;

    public clazz1(int val, int index) {
        this.val = val;
        this.index = index;
    }

    @Override
    public int compareTo(clazz1 o) {
        return o.val.compareTo(this.val);
    }

    @Override
    public String toString() {
        return "clazz1{" +
                "val=" + val +
                ", index=" + index +
                '}';
    }
}
public class SlidingWindowMaximum {

    public static void main(String[] args) {

        int k = 3;
        PriorityQueue<clazz1> priorityQueue = new PriorityQueue<>(k);
        Map<Integer, clazz1> map = new HashMap<>();

        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};

        int w =0;
        boolean startpolling = false;
        for(int i=0; i<arr.length; i++) {

            if ( i == k)
                startpolling = true;


            if(startpolling) {
                System.out.println(priorityQueue.peek());
                clazz1 clazz1 = map.get(w);
                boolean remove = priorityQueue.remove(clazz1);
                w++;
            }

            clazz1 clazz = new clazz1(arr[i], i);
            map.put(i, clazz);
            priorityQueue.offer(clazz);
        }
        System.out.println(priorityQueue.peek());
    }
}
