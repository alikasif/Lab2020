package salesforce;

import java.util.*;

class Port implements Comparable<Port>{
    int node;
    int cost;
    int stops;

    public Port(int n, int c, int k) {
        this.node = n;
        this.cost = c;
        this.stops =k;
    }

    @Override
    public int compareTo(Port o) {
        return this.cost - o.cost;
    }

    @Override
    public boolean equals(Object obj) {
        Port that = (Port)obj;
        return this.node == that.node ;
    }

    @Override
    public String toString() {
        return this.node+" "+this.cost +" "+ this.stops;
    }
}

public class CheapestFlight {
    public static void main(String[] args) {

        int[][] arr = {
/*                {0,1,100},
                {1,2,100},
                {2,0,100},
                {1,3,600},
                {2,3,200}*/
                {0,1,100},
                {0,2,500},
                {1,2,100}
        };

        int src = 0;
        int dest = 2;
        int stops = 1;

        PriorityQueue<Port> pq = new PriorityQueue<>();
        pq.add(new Port(src, 0, 0));

        PriorityQueue<Port> reached = new PriorityQueue<>();

        Set<Integer> visited = new HashSet<>();
        visited.add(src);

        while (!pq.isEmpty()) {

            Port poll = pq.poll();
            System.out.println("popped "+poll);

            if(poll.node == dest) {
                // System.out.println("reached "+ poll);
                // break;
                reached.add(poll);
            }

            if (poll.stops <= stops) {

                for (int i = 0; i < arr.length; i++) {

                    if (arr[i][0] == poll.node) {
                        pq.add(new Port(arr[i][1], poll.cost + arr[i][2], poll.stops+1));
                        visited.add(arr[i][1]);
                        System.out.println("added "+ arr[i][1] +" with cost "+ arr[i][2] +" from "+ poll.node);
                    }
                }
            }
        }
        System.out.println("final cost "+ reached.poll());
    }
}