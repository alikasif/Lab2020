package salesforce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeysNRooms {
    public static void main(String[] args) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(0, List.of(1));
        graph.put(1, List.of(2));
        graph.put(2, List.of(3));

        List<Integer> allNodes = List.of(0, 1, 2, 3);


    }
}
