package google2022;

import java.util.*;

public class GraphPath {

    public static void main(String[] args) {

        int[][] edges = {
                {0,1},
                {0,2},
                {3,5},
                {5,4},
                {4,3}
        };

        int s=0, d=5;
        int[] visited = new int[6];
        findPath(edges, s, d, visited);
    }

    static void findPath(int[][] edges, int s, int d, int[] visited) {

        List<Integer> list = new ArrayList<>();
        list.add(s);

        while (!list.isEmpty()) {
            Integer integer = list.get(0);
            list.remove(integer);
            int[] edge = edges[integer];

            if(edge[0] ==d) {
                System.out.println("found path");
                return;
            }

            if(visited[edge[0]] == 1) {
                continue;
            }
            else {
                visited[edge[0]] = 1;
                list.add(edge[0]);
            }
        }
    }
}
