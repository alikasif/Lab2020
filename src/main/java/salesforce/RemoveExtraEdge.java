package salesforce;

import java.util.*;

public class RemoveExtraEdge {
    public static void main(String[] args) {

        int[][] mat = { {0,1,0,1,1},
                        {1,0,1,0,0},
                        {0,1,0,1,0},
                        {1,0,1,0,0},
                        {1,0,0,0,0}
        };

        List<Integer> allNodes = List.of(0,1,2,3,4);

        Map<Integer, List<Integer>> edges = new HashMap<>();
        edges.put(0, List.of(1,3,4));
        edges.put(1, List.of(0,2));
        edges.put(2, List.of(1,3));
        edges.put(3, List.of(0,2));
        edges.put(4, List.of(0));

        Set<Integer> visited = new HashSet<>();
        int c = 0;

        for(int e : allNodes) {
            for(int i : edges.get(e)) {
                mat[e][i] =0;
                Set<Integer> reachable = checkReachibility(mat, e, allNodes);
                if (reachable.size() == allNodes.size()) {
                    System.out.println(e +" => "+i);
                    c++;
                }
                mat[e][i] =1;
            }
        }
        System.out.println(c);
    }

    static Set<Integer> checkReachibility(int[][] mat, int node, List<Integer> allNodes) {

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        stack.push(node);
        visited.add(node);
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            for(Integer e : allNodes) {
                if(mat[pop][e] == 1 && !visited.contains(e)) {
                    stack.push(e);
                    visited.add(e);
                }
            }
        }
       // System.out.println(node+" "+visited);
        return visited;
    }
}
