package atlassian;

import java.util.*;

public class ReconstructIternary {

    Map<String, PriorityQueue<String>> adjacencyList;
    LinkedList<String> path;

    public List<String> findItinerary(String[][] tickets) {
        adjacencyList = new HashMap();
        path = new LinkedList<>();
        for (String[] ticket : tickets) {
            adjacencyList.putIfAbsent(ticket[0], new PriorityQueue<>());
            adjacencyList.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK");
        return path;
    }

    public void dfs(String departure) {
        PriorityQueue<String> arrivals = adjacencyList.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.addFirst(departure);
    }
    public static void main(String[] args) {

    }
}
