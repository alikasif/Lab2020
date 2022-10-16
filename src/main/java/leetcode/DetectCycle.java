package leetcode;

import java.util.*;

public class DetectCycle {

    public static void main(String[] args) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        adjList.put(2, Arrays.asList(3));
        adjList.put(3, Arrays.asList(1));
        adjList.put(4, Arrays.asList(0,1));
        adjList.put(5, Arrays.asList(0,2));


        List<Integer> stack = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> printStack = new ArrayList<>();
        Set<Integer> vertices = new HashSet<>();
        vertices.add(0); vertices.add(1); vertices.add(2); vertices.add(3);vertices.add(4);

        stack.add(5);
        visited.add(5);

        while ( !stack.isEmpty() ) {

            Integer pop = stack.get(stack.size() - 1);
            stack.remove(pop);
            printStack.add(pop);

            if( adjList.get(pop) == null ) {
                if(stack.isEmpty()) {
                    if (!vertices.isEmpty()) {
                        Integer v = vertices.iterator().next();
                        stack.add(v);
                        vertices.remove(v);
                    }
                }
                continue;
            }

            for (Integer e : adjList.get(pop)) {
                 if (!visited.contains(e)) {
                     stack.add(e);
                     visited.add(e);
                     vertices.remove(e);
                 }
            }
        }
        System.out.println(printStack);
    }
}
