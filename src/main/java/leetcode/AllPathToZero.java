package leetcode;

import java.util.*;

public class AllPathToZero {

    public static void main(String[] args) {

        int[][] connections = {
                {0,1}, {1,3}, {2,3}, {4,0}, {4,5}
        };

        Map<Integer, Integer> paths = new HashMap<>();
        paths.put(0,1); paths.put(1,3);
        paths.put(2,3); paths.put(4,0);
        paths.put(4,5);

        List<Integer> stack = new ArrayList<>();

        /*
        put first node in stack. start the loop. pop tos. find connected node and mark path has visited
        if there is node then push the tossed and connected node in stack.
        if not and there are no more connected node for this then toss next node from stack and continue.
         */

        stack.add(0);
        Set<String> visited = new HashSet<>();

        while (!stack.isEmpty()) {

            Integer t = stack.get(stack.size()-1);
            Integer n = paths.get(t);

            if (n != null && !visited.contains(t+"->"+n)) {
                stack.add(n);
                visited.add(t+"->"+n);
                continue;
            }
            else {
                Integer remove = stack.remove(stack.size() - 1);
                System.out.println(remove);
            }
        }
    }
}
