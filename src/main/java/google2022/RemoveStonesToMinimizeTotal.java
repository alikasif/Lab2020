package google2022;

import java.util.PriorityQueue;

public class RemoveStonesToMinimizeTotal {
    public static void main(String[] args) {
        int k=3;
        int arr[] = {4,3,6,7};
        int sum =0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(arr.length);

        for( int x : arr) {
            pq.add(-1*x);
            sum+=x;
        }

        while (k >0) {
            int x = -1*(pq.poll());
            sum-=x;
            x = (x%2==0)? x/2 : (x/2)+1;
            pq.add(x*-1);
            sum+=x;
            k--;
        }
        System.out.println(sum);
    }
}
