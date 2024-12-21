package google2024.medium;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/course-schedule/solutions/58586/python-20-lines-dfs-solution-sharing-with-explanation/
// https://leetcode.com/problems/course-schedule/solutions/162743/java-c-python-bfs-topological-sorting-o-n-e/

public class CourseSchedule {

    static void topoSort(int[][] mat) {

        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        int i=0;
        while( i < mat.length) {
            inDegree.putIfAbsent(mat[i][0], 0);
            inDegree.putIfAbsent(mat[i][1], 0);
            inDegree.put(mat[i][1], inDegree.getOrDefault(mat[i][1], 0)+1);
            List<Integer> list = adjList.getOrDefault(mat[i][0], new ArrayList<>());
            list.add(mat[i][1]);
            adjList.put(mat[i][0], list);
            i++;
        }

        List<Integer> zeroIndegreeNodes = inDegree.keySet().stream().filter(k -> inDegree.get(k) == 0).collect(Collectors.toList());
        System.out.println(inDegree);
        System.out.println(adjList);
        System.out.println(zeroIndegreeNodes);

        List<Integer> visited = new ArrayList<>();

        while (!zeroIndegreeNodes.isEmpty()) {
            Integer node = zeroIndegreeNodes.remove(0);
            visited.add(node);

            if(!adjList.containsKey(node))
                continue;

            for(int n : adjList.get(node)) {

                if(visited.contains(n))
                    continue;

                Integer k = inDegree.get(n);

                if( k  == 1 ) {
                    zeroIndegreeNodes.add(n);
                }
                else {
                    inDegree.put(n, k-1);
                }
            }
        }
        System.out.println(visited);

    }

    public static void main(String[] args) {

/*
        int[][]mat = {
                {0,0,1,1,0},
                {0,0,1,0,1},
                {0,0,0,1,0},
                {0,0,0,0,0},
                {1,0,1,0,0}
        };
*/
        int[][] mat = {
                {0, 1},
                {1, 2},
                {0, 3},
                {3, 4}
        };

        topoSort(mat);

/*
        int[] outDegree = new int[5];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0; i<mat.length; i++) {
            int n1 = mat[i][0];
            int n2 = mat[i][1];

            outDegree[n1]++;
            List<Integer> list = map.getOrDefault(n1, new ArrayList<>());
            list.add(mat[i][1]);
            map.put(n1, list);
        }

        System.out.println(Arrays.toString(outDegree));
        System.out.println(map);

        List<Integer> nodeWithZeroOutdegree = new ArrayList<>();
        for(int i=0; i<outDegree.length; i++) {
            if(outDegree[i] == 0)
                nodeWithZeroOutdegree.add(i);
        }
        System.out.println(nodeWithZeroOutdegree);

        List<Integer> tpo = new ArrayList<>();
*/

       /* while (!nodeWithZeroOutdegree.isEmpty()) {
            Integer remove = nodeWithZeroOutdegree.remove(0);
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
        }*/
//        System.out.println(tpo);
    }
}
