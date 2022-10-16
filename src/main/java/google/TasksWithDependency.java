package google;

import java.util.*;

enum Color{
    WHITE,
    GREY,
    BLACK
}

class GNode {
    int vertex;
    Color color;

    public GNode(int i, Color c) {
        vertex = i;
        color =c;
    }
}

public class TasksWithDependency {

    public static void main(String[] args) {

        Integer[][] arr = {
                {2,1},
                {3,2},
                {1,0}
        };
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        List<Integer> adjs = new ArrayList<>();
        adjs.add(1);
        //adjs.add(3);
        adjList.put(2, adjs);

        adjList.put(3, Arrays.asList(arr[1][1]));
        adjList.put(1, Arrays.asList(arr[2][1]));
        System.out.println(adjList);
        int V = 4;

        Map<Integer, Color> visited = new HashMap<>();
        visited.put(0, Color.WHITE);
        visited.put(1, Color.WHITE);
        visited.put(2, Color.WHITE);
        visited.put(3, Color.WHITE);

        for(int i =0; i<V; i++) {
            System.out.println("checking at "+i);
            if( visited.get(i) == Color.WHITE) {
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                visited.put(i, Color.GREY);
                boolean cycle = processDFSTree(adjList, stack, visited);
                if(cycle) {
                    System.out.println("found cycle at "+i);
                    return;
                }
            }
        }
    }

    private static boolean processDFSTree(Map<Integer, List<Integer>> adjList, Stack<Integer> stack, Map<Integer, Color> visited) {

        if(adjList.get(stack.peek()) == null) {
            visited.put(stack.peek(), Color.BLACK);
            return false;
        }

        for(Integer v : adjList.get(stack.peek())) {
            if(visited.get(v) == Color.GREY) {
                System.out.println("found cycle at "+v);
                printCycle(stack, v);
                return true;
            }
            else if(visited.get(v) == Color.WHITE) {
                stack.push(v);
                visited.put(v, Color.GREY);
                return processDFSTree(adjList,stack,visited);
            }
        }
        visited.put(stack.peek(), Color.BLACK);
        stack.pop();
        return false;
    }

    private static void printCycle(Stack<Integer> stack, Integer v) {

        Stack<Integer> stack2 = new Stack<>();
        stack2.push(stack.pop());

        while (!stack.isEmpty() && stack2.peek() != v) {
            stack2.push(stack.pop());
        }

        while (!stack2.isEmpty()) {
            System.out.println(stack2.peek());
            stack.push(stack2.pop());
        }
    }
}
