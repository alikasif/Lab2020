package atlassian;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {

        int[][]mat = {
                {0,0,1,1,0},
                {0,0,1,0,1},
                {0,0,0,1,0},
                {0,0,0,0,0},
                {1,0,1,0,0}
        };

        int[] indegree = new int[5];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0; i<mat.length; i++) {
            for(int j=0; j<mat.length; j++) {
                if( mat[i][j] == 1) {
                    indegree[j]++;
                    List<Integer> lst = map.get(i);
                    if(lst == null)
                        lst = new ArrayList<>();
                    lst.add(j);
                    map.put(i, lst);
                }
            }
        }

        List<Integer> nodeWithZeroIndegree = new ArrayList<>();
        for(int i=0; i<indegree.length; i++) {
            if(indegree[i] ==0)
                nodeWithZeroIndegree.add(i);
        }

        System.out.println(Arrays.toString(indegree));
        System.out.println(nodeWithZeroIndegree);
        System.out.println(map);

        List<Integer> tpo = new ArrayList<>();

        while (!nodeWithZeroIndegree.isEmpty()) {
            Integer remove = nodeWithZeroIndegree.remove(0);
            tpo.add(remove);
            if(map.get(remove) == null) {
                System.out.println("no neighbor for " + remove);
            }
            else {
                for (int k : map.get(remove)) {
                    indegree[k]--;
                    if (indegree[k] <= 0)
                        nodeWithZeroIndegree.add(k);
                }
            }
        }
        System.out.println(tpo);
    }
}