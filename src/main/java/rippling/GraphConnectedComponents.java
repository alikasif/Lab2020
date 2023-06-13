package rippling;

import java.util.*;

public class GraphConnectedComponents {
    public static void main(String[] args) {

        int[][] mat = {{1,1,0},
                        {1,1,0},
                        {0,0,1}
                    };

        List<Integer> allNodes = List.of(0,1,2);
        Set<Integer> visited = new HashSet<>();
        int c =0;
        for(int e : allNodes) {
            if(!visited.contains(e)) {
                Set<Integer> reachable = checkReachibility(mat, e, allNodes);
                visited.addAll(reachable);
                c++;
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
                if(mat[node][e] == 1 && !visited.contains(e)) {
                    stack.push(e);
                    visited.add(e);
                }
            }
        }
         System.out.println(node+" "+visited);
        return visited;
    }
}
