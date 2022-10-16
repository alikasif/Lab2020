package leetcode;

import java.util.*;

public class MotherVertexGraph {

    public static void main(String[] args) {

        Map<Integer, List<Integer>> dag = new HashMap<>();
        dag.put(1, Arrays.asList(0));
        dag.put(2, Arrays.asList(1));
        dag.put(0, Arrays.asList(2, 3));
        dag.put(3, Arrays.asList(4));

        List<Integer> stack = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> unvisited = new HashSet<>();
        unvisited.add(0); unvisited.add(1); unvisited.add(2); unvisited.add(3); unvisited.add(4);

        stack.add(0);
        visited.add(0);
        unvisited.remove(0);
        Map<Integer, List<Integer>> pathLength = new HashMap<>();
        Integer pop= null;
        while (!stack.isEmpty()) {

            pop = stack.get(stack.size()-1);
            stack.remove(pop);
            System.out.println(pop);

//            if(dag.get(pop) == null) {
//                Integer next = unvisited.iterator().next();
//                stack.add(next);
//                visited.add(next);
//                unvisited.remove(next);
//                continue;
//            }

            if(dag.get(pop) != null) {
                for (Integer e : dag.get(pop)) {
                    if (!visited.contains(e)) {
                        visited.add(e);
                        unvisited.remove(e);
                        stack.add(e);
                    }
                }
            }
        }
        System.out.println("last vertex "+ pop);
        stack.clear(); visited.clear(); unvisited.clear();
        pathLength.clear();

        stack.add(pop);
        visited.add(pop);
        unvisited.remove(0);
        while (!stack.isEmpty()) {

            pop = stack.get(stack.size()-1);
            stack.remove(pop);
            System.out.println(pop);

            if(dag.get(pop) == null && !unvisited.isEmpty()) {
                Integer next = unvisited.iterator().next();
                stack.add(next);
                visited.add(next);
                unvisited.remove(next);
                continue;
            }
            else if(dag.get(pop) != null) {
                for (Integer e : dag.get(pop)) {
                    if (!visited.contains(e)) {
                        visited.add(e);
                        unvisited.remove(e);
                        stack.add(e);
                    }
                }
            }
        }
        System.out.println("last vertex "+ pop);
    }
}
