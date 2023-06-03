package salesforce;

import java.util.*;

public class EventualSafeState {
    public static void main(String[] args) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, List.of(1,2));
        graph.put(1, List.of(2,3));
        graph.put(2, List.of(5));
        graph.put(3, List.of(0));
        graph.put(4, List.of(5));

        Set<Integer> terminalNodes = new HashSet<>();
        terminalNodes.add(5);
        terminalNodes.add(6);

        Set<Integer> allNodes = new HashSet<>();
        allNodes.add(0); allNodes.add(1); allNodes.add(2);
        allNodes.add(3); allNodes.add(4);allNodes.add(5);
        allNodes.add(6);

        for(int e : allNodes) {
            boolean checkCycle = reachedTerminalState(graph, terminalNodes, e);
            System.out.println(e+" reached terminal state => "+checkCycle);
        }

    }

    static boolean reachedTerminalState(Map<Integer, List<Integer>> graph, Set<Integer> terminalNodes, int node) {

        if(graph.get(node) == null)
            return true;

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        stack.add(node);
        visited.add(node);
        boolean cycleFound = false;
        boolean reachedTerminalNode = false;

        while (!stack.isEmpty()) {

            Integer pop = stack.pop();
            if( graph.get(pop) == null)
                continue;

            for(int e : graph.get(pop)) {
                if(e == node) {
                    //System.out.println("cycle..found");
                    cycleFound = true;
                    continue;
                }
                if(terminalNodes.contains(e)) {
                    reachedTerminalNode = true;
                }
                if(!visited.contains(e)) {
                    stack.push(e);
                    visited.add(e);
                }
            }
        }
        //System.out.println(node +" "+ cycleFound+" "+ reachedTerminalNode);
        return !cycleFound && reachedTerminalNode;
    }
}
