package atlassian;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ReachableCityWithinThreshold {
    public static void main(String[] args) {

        int[][] mat = {
                {0,1,1},
                {1,2,1},
                {2,3,3},
                {3,4,1},
                {0,3,3}
        };

        Map<Integer, Integer> cityConnection = new HashMap<>();
        Map<Integer, Integer> cityCost = new HashMap<>();

        // dfs from each node and count distance

        for(int i=0; i<5; i++) {

            Stack s = new Stack<Integer>();
            s.add(i);

            while (!s.isEmpty()) {

            }
        }

    }
}
