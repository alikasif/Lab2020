package atlassian;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public class BusRoute {
    public static void main(String[] args) {

        // 3 1 5 6
        // 3 5 7 8
        //             |--- - - - 7 - - -8
        //             |
        //   3 -- 1 -- 5 -- 6
        //   |         |
        //   |- - - - -|

        int[][] routes = new int[][] {
            {3,1,5,6},
            {3,5,7,8}
        };
        int buses = numBusesToDestination(routes, 6, 8);
        System.out.println(buses);
    }

    public static int numBusesToDestination(int[][] routes, int S, int T) {
        int n = routes.length;
        HashMap<Integer, HashSet<Integer>> to_routes = new HashMap<>();
        for (int i = 0; i < routes.length; ++i) {
            for (int j : routes[i]) {
                if (!to_routes.containsKey(j))
                    to_routes.put(j, new HashSet<Integer>());
                to_routes.get(j).add(i);
            }
        }
        System.out.println(to_routes);

        Queue<int[]> bfs = new ArrayDeque();
        bfs.offer(new int[] {S, 0});
        HashSet<Integer> seen = new HashSet<>();
        seen.add(S);
        boolean[] seen_routes = new boolean[n];
        while (!bfs.isEmpty()) {
            int stop = bfs.peek()[0], bus = bfs.peek()[1];
            bfs.poll();

            if (stop == T)
                return bus;

            for (int i : to_routes.get(stop)) {

                if (seen_routes[i])
                    continue;

                for (int j : routes[i]) {
                    if (!seen.contains(j)) {
                        seen.add(j);
                        bfs.offer(new int[] {j, bus + 1});
                    }
                }
                seen_routes[i] = true;
            }
        }
        return -1;
    }
}
