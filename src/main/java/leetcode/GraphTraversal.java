package leetcode;

import java.util.*;

public class GraphTraversal {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        adjList.put(0, Arrays.asList(1,4));
        adjList.put(1, Arrays.asList(0,2,3,4));
        adjList.put(2, Arrays.asList(1,3));
        adjList.put(3, Arrays.asList(1,2, 4));
        adjList.put(4, Arrays.asList(0, 1,3));

        System.out.println(adjList);

//        IterativeDFS(adjList);
//        System.out.println("recursive");
//        recursiveDFS(adjList, new HashSet<>(), 3);

        iterativeBFS(adjList);
    }

    static void iterativeBFS(Map<Integer, List<Integer>> adjList) {

        List<Integer> queue = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(3);
        visited.add(3);

        while (!queue.isEmpty()) {

            //  System.out.println(stack);
            Integer top = queue.get(0);
            queue.remove(top);

            System.out.println(top);

            for(Integer i : adjList.get(top)) {
                if(!visited.contains(i)) {
                    queue.add(i);
                    visited.add(i);
                }
            }
        }
    }

    static void IterativeDFS(Map<Integer, List<Integer>> adjList) {

        List<Integer> stack = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        stack.add(3);
        visited.add(3);

        while (!stack.isEmpty()) {

          //  System.out.println(stack);
            Integer top = stack.get(stack.size()-1);
            stack.remove(top);

            System.out.println(top);

            for(Integer i : adjList.get(top)) {
                if(!visited.contains(i)) {
                    stack.add(i);
                    visited.add(i);
                }
            }
        }
    }

    static void recursiveDFS(Map<Integer, List<Integer>> adjList, Set<Integer> visited, int v) {

        visited.add(v);
        System.out.println(v);

        for(Integer i : adjList.get(v)) {
            if(!visited.contains(i)) {
                recursiveDFS(adjList, visited, i);
            }
        }
    }
}
