package leetcode;

import java.util.*;

public class Prims {

    public static void main(String[] args) {

        int[][] graph = new int[][] {
                {0,4,0,0,0,0,0,8,0},
                {4,0,8,0,0,0,0,11,0},
                {0,8,0,7,0,4,0,0,2},
                {0,0,7,0,9,14,0,0,0},
                {0,0,0,9,0,10,0,0,0},
                {0,0,4,14,10,0,2,0,0},
                {0,0,0,0,0,2,0,1,6},
                {8,11,0,0,0,0,1,0,7},
                {0,0,2,0,0,0,6,7,0}
        };

        Set<Integer> visited = new HashSet<>();
        Set<Integer> notVisited = new HashSet<>();
        notVisited.add(1);
        notVisited.add(2);
        notVisited.add(3);
        notVisited.add(4);
        notVisited.add(5);
        notVisited.add(6);
        notVisited.add(7);
        notVisited.add(8);

        visited.add(0);
        List<String> path = new ArrayList<>();

        while (true) {
            int dist = Integer.MAX_VALUE;
            Integer s = -1;
            Integer d = -1;
            for(int k : visited) {
                for(int j : notVisited) {
                    if(graph[k][j] != 0 && graph[k][j] < dist) {
                        dist = graph[k][j];
                        s = k;
                        d = j;
                    }
                }
            }
            if(dist == Integer.MAX_VALUE)
                break;
            visited.add(d);
            notVisited.remove(d);
            path.add(s+"|"+d+" => "+dist);
        }
        System.out.println(path);
    }
}