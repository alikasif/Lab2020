package google2024.hard;

import java.util.*;

class RouteCount {
    int route;
    int count;
    public RouteCount(int r, int c) {
        this.route =r;
        this.count =c;
    }

    @Override
    public String toString() {
        return "RouteCount{" +
                "route=" + route +
                ", count=" + count +
                '}';
    }
}
public class BusRoute {

    static void createAdjMap(int[][] arr) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();

        int i=0;
        while(i < arr.length) {
            int[] x = arr[i];

            for(int j=1; j<x.length; j++) {

                adjMap.putIfAbsent(x[j-1], new ArrayList<>());
                List<Integer> integers = adjMap.get(x[j-1]);
                integers.add(x[j]);
            }
            adjMap.putIfAbsent(x[x.length-1], new ArrayList<>());
            List<Integer> integers = adjMap.get(x[x.length-1]);
            integers.add(x[0]);

            i++;
        }
        System.out.println(adjMap);
        bfs(adjMap);
    }

    static void bfs( Map<Integer, List<Integer>> adjMap) {
        int start = 15;
        int stop = 12;
        int min = Integer.MAX_VALUE;
        Queue<RouteCount> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(new RouteCount(start, 1));
        visited.add(start);
        while (!queue.isEmpty()) {
            System.out.println("\n queue :: "+queue);
            RouteCount poll = queue.poll();
            List<Integer> integers = adjMap.get(poll.route);
            //System.out.print(poll +" -> ");
            for(int x : integers) {
                if (x == stop) {
                    RouteCount rc = new RouteCount(x, poll.count);
              //      System.out.print(rc);
                //    System.out.println("\ngot path");
                    min = Math.min(min, rc.count);
                }
                else {
                    if(!visited.contains(x)) {
                        queue.offer(new RouteCount(x, poll.count+1));
                        visited.add(x);
                    }
                }
            }
        }
        System.out.println("min == > "+min);
    }

    public static void main(String[] args) {

        int[][] arr = {
                    {7, 12},
                    {4, 5, 15},
                    {6},
                    {15, 19},
                    {5, 12, 13}
        };
        int start = 15;
        int stop = 12;
        createAdjMap(arr);
        /*List<int[]> list = new ArrayList<>();
        for(int[] x : arr)
            list.add(x);

        Set<Integer> set = new HashSet<>();
        set.add(start);

        int i=0;
        boolean change = true;

        while (change) {

            System.out.println(set);

            change = false;
            boolean found = false;
            Set<Integer> tmp = new HashSet<>();
            System.out.println("processing " + Arrays.toString(list.get(i)));
            for(int x : list.get(i)) {
                if (set.contains(x)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                for(int x : list.get(i))
                    set.add(x);
                list.remove(i);
                i=0;
            }
            else {
                i++;
            }

            if (i == list.size())
                break;

            if(found || i < list.size())
                change = true;
        }

        System.out.println(set);
        System.out.println(Arrays.toString(arr));*/
    }
}
