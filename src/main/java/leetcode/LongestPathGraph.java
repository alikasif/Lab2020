package leetcode;

import java.util.*;

public class LongestPathGraph {

    public static void main(String[] args) {

        Map<String, Integer> length = new HashMap<>();
        length.put("1|2", 3);length.put("2|1", 3);
        length.put("2|3", 4);length.put("3|2", 4);
        length.put("2|6", 2);length.put("6|2", 2);
        length.put("6|4", 6);length.put("4|6", 6);
        length.put("6|5", 5);length.put("5|6", 5);
        length.put("4|1", 7);length.put("1|4", 7);

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        adjList.put(2, Arrays.asList(1, 3, 6));
        adjList.put(1, Arrays.asList(2,4));
        adjList.put(3, Arrays.asList(2));
        adjList.put(4, Arrays.asList(6));
        adjList.put(5, Arrays.asList(6));
        adjList.put(6, Arrays.asList(2,5,4));

        Map<String, Integer> pathLenghts = new HashMap<>();

        for(int i = 1; i < 7; i++) {
            List<Integer> stack = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();

            stack.add(i);
            visited.add(i);

            while (!stack.isEmpty()) {
                Integer pop = stack.get(stack.size()-1);
                stack.remove(pop);

                for(Integer e : adjList.get(pop)) {

                    if(!visited.contains(e)) {
                        stack.add(e);
                        visited.add(e);

                        String k1 = pop+"|"+e;
                        String k2 = e+"|"+pop;

                        int l = length.get(k1) == null?length.get(k2) == null?0:length.get(k2): length.get(k1);

                        int el = pathLenghts.get(i+"|"+e) == null? 0: pathLenghts.get(i+"|"+e);

                        pathLenghts.put(i+"|"+e, Math.max(el, pathLenghts.get(i+"|"+pop) == null? l: l + pathLenghts.get(i+"|"+pop)));
                    }
                }
            }
        }
        System.out.println(pathLenghts);
    }
}
