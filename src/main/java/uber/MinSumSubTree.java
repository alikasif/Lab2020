package uber;

import java.util.*;

public class MinSumSubTree {
    public static void main(String[] args) {
        Map<Integer, Integer> weights = new HashMap<>();
        int[][] matrix = {
                {0,1,1,1,0,0,0},
                {1,0,0,0,0,0,0},
                {1,0,0,0,1,1,0},
                {1,0,0,0,0,0,1},
                {0,0,1,0,0,0,0},
                {0,0,1,0,0,0,0},
                {0,0,0,1,0,0,0}
        };
        weights.put(0,4);
        weights.put(1,2);
        weights.put(2,1);
        weights.put(3,6);
        weights.put(4,3);
        weights.put(5,5);
        weights.put(6,2);
        int minw = Integer.MAX_VALUE;
        Set<String> visited = new HashSet<>();
        for(int i =0; i<matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
                if(matrix[i][j] == 1 && !visited.contains(i+""+j) && !visited.contains(j+""+i)) {
                    visited.add(i+""+j);
                    visited.add(j+""+i);
                    int s1 = getWeightSum(matrix, weights, i, j);
                    int s2 = getWeightSum(matrix, weights, j, i);
                    System.out.println(i +" "+s1+" "+j+" "+s2);
                    minw = Math.min(minw, Math.abs(s1-s2));
                }
            }
        }
        System.out.println(minw);
    }

    private static int getWeightSum(int[][] matrix, Map<Integer, Integer> weights, int i, int j) {
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        int ws = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(i);
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            ws = ws + weights.get(pop);
            for(int k =0; k<matrix.length; k++) {
                if(matrix[pop][k] == 1 && k != j && !visited.contains(k)) {
                    visited.add(k);
                    stack.push(k);
                }
            }
        }
        return ws;
    }
}
