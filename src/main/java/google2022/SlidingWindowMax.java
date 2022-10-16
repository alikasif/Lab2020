package google2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMax {

    public static void main(String[] args) {

        // int[] arr = {-4,-3,-8,-9,-0,-1,-9};
        // int[] arr = {-9, -8, -6, -4, -3, -1};
        int[] arr = {-1,-2,-3,-4,-10,-6,-9,-8,-7,-5};
        int k = 3;
        int n = arr.length;

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        PriorityQueue<Integer> toDrop = new PriorityQueue<>(k);

        for(int i = 0;i<k;i++)
            pq.add(arr[i]);

        List<Integer> ans = new ArrayList<>();

        // store the maximum in ans
        ans.add(pq.peek());
        System.out.println(Arrays.toString(arr));
        //iterate for rest elements
        for(int i = k; i<n; i++)
        {
            // pop the heap if leftmost element is previous element was maximum
            if(arr[i-k] == pq.peek()) {
                pq.poll();
            }
            else
            {
                // push the leftmost element to toDrop heap
                toDrop.add(arr[i-k]);
            }
            System.out.println(Arrays.toString(arr) +" || "+pq.toString() +" || "+ toDrop.toString());
            // pop elements from both heap till their top matches

            while(!toDrop.isEmpty() && toDrop.peek() == pq.peek())
            {
                pq.poll();
                toDrop.poll();
            }
            // push the element to the heap
            pq.add(arr[i]);
            ans.add(pq.peek());
        }
        System.out.println(ans);
    }
}
