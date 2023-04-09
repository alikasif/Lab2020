package uber;

import java.util.*;

public class GraphColoring {
    public static void main(String[] args) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        List<Integer> vertices = new ArrayList<>();
        vertices.add(1);
        //vertices.add(2);
        vertices.add(3);
        adjList.put(0, vertices);

        vertices = new ArrayList<>();
        vertices.add(0);
        vertices.add(2);
        adjList.put(1, vertices);

        vertices = new ArrayList<>();
        //vertices.add(0);
        vertices.add(1);
        vertices.add(3);
        adjList.put(2, vertices);

        vertices = new ArrayList<>();
        vertices.add(0);
        vertices.add(2);
        adjList.put(3, vertices);

        Map<Integer, Integer> color = new HashMap<>();

        for(Integer k : adjList.keySet()) {
            if(color.get(k) == null)
                if(!BFS(k, adjList, color)) {
                    System.out.println("not bipartite");
                    break;
                }
        }
    }

    private static boolean BFS(Integer v, Map<Integer, List<Integer>> adjList, Map<Integer, Integer> color) {

        Queue<Integer> q = new LinkedList();
        color.put(v, 0);
        q.add(v);

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            for(Integer e : adjList.get(poll)) {
                if(color.get(e) == null) {
                    color.put(e, 1-color.get(poll));
                    q.add(e);
                }
                else if (color.get(e) == color.get(poll)){
                    return false;
                }
            }
        }
        return true;
    }
}
