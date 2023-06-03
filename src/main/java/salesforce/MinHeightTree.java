package salesforce;

import scala.Int;

import java.util.*;

public class MinHeightTree {
    public static void main(String[] args) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        adjList.put(0, List.of(1));
        adjList.put(1, List.of(0,2,3));
        adjList.put(2, List.of(1));
        adjList.put(3, List.of(1));

        System.out.println(adjList);
        for(int e : adjList.keySet())
            findLeafNodes(adjList, e);
        // find a node whose distance from all leaf is minimum

    }

    static void findLeafNodes( Map<Integer, List<Integer>> adjList, int root) {
        Stack<Integer> stack = new Stack<>();
        stack.push(root);
        int ht = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(root);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            boolean found = false;
            for(int e : adjList.get(node)) {
                if(!visited.contains(e)){
                    visited.add(e);
                    stack.push(e);
                    found = true;
                }
            }
            if(found)
                ht++;
        }
        System.out.println(root +" "+ visited +"  " + ht);
    }
}
