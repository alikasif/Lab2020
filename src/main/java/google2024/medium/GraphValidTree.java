package google2024.medium;

import java.util.*;

public class GraphValidTree {
    public static void main(String[] args) {

        //int[][] edges = {{0,1}, {0,2}, {0,3}, {1,4}};
        int[][] edges = {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
        Map<Integer, Set<Integer>> parents = new HashMap<>();

        for(int[] edge : edges) {
            int p = edge[0];
            int c = edge[1];

            Set<Integer> parent = parents.get(c);
            if(parent == null) {
                parent = new HashSet<>();
                parent.add(p);
                parents.put(c, parent);
            }
            else {
                if (parent.contains(p))
                    continue;
                else
                    System.out.println("invalid tree "+ Arrays.toString(edge));
            }
        }

    }
}
