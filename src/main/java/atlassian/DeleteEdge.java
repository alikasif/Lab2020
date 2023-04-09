package atlassian;

import java.util.*;

public class DeleteEdge {

    public static void main(String[] args) {

        int[] vertex = { 4, 2, 1, 6, 3, 5, 2 };

        int[][] edges = {
                { 0, 1 },
                { 0, 2 },
                { 0, 3 },
                { 2, 4 },
                { 2, 5 },
                { 3, 6 }
        };

        Map<Integer, List<Integer>> map = new HashMap<>();

        // create adjacency list
        for(int i=0; i<edges.length; i++) {

            int[] x = edges[i];
            List<Integer> integers = map.get(x[0]);

            if(integers == null)
                integers = new ArrayList<>();

            integers.add(x[1]);
            map.put(x[0], integers);

            integers = map.get(x[1]);
            if(integers == null)
                integers = new ArrayList<>();

            integers.add(x[0]);
            map.put(x[1], integers);
        }

        System.out.println(map);

        List<Integer> allv = new ArrayList<>();
        for(int i =0; i<=6;i++)
            allv.add(i);

        // apply dfs at each node
        for(int i=0; i<edges.length; i++) {

            List<Integer> visited = new ArrayList<>();

            int e1 = edges[i][0];
            int e2 = edges[i][1];

            dfs(map, e1, e2, visited);

            ArrayList<Integer> integers = new ArrayList<>(allv);
            integers.removeAll(visited);

            int s1=0;
            for(int k : visited) {
                s1 = s1+ vertex[k];
            }

            int s2=0;
            for(int k : integers) {
                s2 = s2 + vertex[k];
            }
            System.out.println(e1 +":"+ s1 + " " +e2 + ":"+s2+" "+ Math.abs(s1-s2));
        }
    }

    static void dfs (Map<Integer, List<Integer>> map, Integer v1, Integer v2, List<Integer> visited ) {

       // System.out.println(visited);
        List<Integer> integers = new ArrayList<>(map.get(v1));
        if(integers != null && !integers.isEmpty()) {
            integers.remove(v2);
            for (int k : integers) {
                if(!visited.contains(k)) {
                    visited.add(k);
                    dfs(map, k, v2, visited);
                }
            }
        }
    }
}
