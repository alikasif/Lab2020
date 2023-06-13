package rippling;

import java.util.*;

public class NetworkBridge {
    public static void main(String[] args) {

        int[][] arr = {
                {0,1,1,0},
                {1,0,1,1},
                {1,1,0,0},
                {0,1,0,0}
        };
        usingTarjan(arr);
        System.out.println();
        usingTarjanRecursive(9, arr);
    }

    static void usingTarjanRecursive(int n, int[][] arr) {

        int[] ids, low;
        int id = 1;

        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer,List<Integer>> edgeMap = new HashMap<>();

        ids = new int[n];
        low = new int[n];
        Arrays.fill(ids, 0);
        Arrays.fill(low, 0);

/*
        edgeMap.put(0, List.of(1,2));
        edgeMap.put(1, List.of(0,2,3));
        edgeMap.put(2, List.of(0,1));
        edgeMap.put(3, List.of(1));
*/
        edgeMap.put(0, List.of(1,2));
        edgeMap.put(1, List.of(0,2));
        edgeMap.put(2, List.of(0,1,3,5));
        edgeMap.put(3, List.of(2,4));
        edgeMap.put(4, List.of(3));
        edgeMap.put(5, List.of(2,6,8));
        edgeMap.put(6, List.of(5,7));
        edgeMap.put(7, List.of(6,8));
        edgeMap.put(8, List.of(5,7));

        dfs(0, -1, ids, low, id, edgeMap, ans);
        System.out.println(ans);
        System.out.println(Arrays.toString(ids));
        System.out.println(Arrays.toString(low));
    }

    static void dfs(int curr, int prev, int[] ids, int[] low, int id, Map<Integer,List<Integer>> edgeMap, List<List<Integer>> ans) {
        System.out.println("ids => "+Arrays.toString(ids));
        System.out.println("low => " + Arrays.toString(low));

        ids[curr] = low[curr] = id;
        id++;

        for (int next : edgeMap.get(curr)) {
            if (ids[next] == 0) {
                dfs(next, curr, ids, low, id, edgeMap, ans);
                low[curr] = Math.min(low[curr], low[next]);
            }
            else if (next != prev)
                low[curr] = Math.min(low[curr], ids[next]);

            if (ids[curr] < low[next] )
                ans.add(Arrays.asList(curr, next));
        }
    }

    static void usingTarjan(int[][] arr) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, List.of(1,2));
        map.put(1, List.of(0,2,3));
        map.put(2, List.of(0,1));
        map.put(3, List.of(1));

        int [] ids = {0,0,0,0};
        int [] lowLink = {0,1,2,3};
        boolean [] visited = {false, false, false, false};

        List<String> bridges = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        int id = 0;
        stack.add(0);

        while (!stack.isEmpty()) {

            Integer pop = stack.pop();
            ids[pop] = id;
            lowLink[pop] = id;
            visited[pop] = true;
            id++;

            for(int v : map.get(pop)) {

                if (!visited[v]) {
                    visited[v] = true;
                    stack.push(v);
                    int minLowLink = Math.min(lowLink[pop], lowLink[v]);
                    lowLink[pop] = minLowLink;
                    if(ids[pop] < lowLink[v])
                        bridges.add(pop+"->"+v);
                }
                else {
                    int minLowLink = Math.min(lowLink[pop], ids[v]);
                    lowLink[pop] = minLowLink;
                }
            }
        }
        System.out.println(Arrays.toString(lowLink));
        System.out.println(Arrays.toString(ids));
        System.out.println(bridges);
    }

    static void BridgeUsingDegreee(int[][] arr) {

        int[] degree = new int[4];
        Set<String> visited = new HashSet<>();
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                if( arr[i][j] == 1 && !(visited.contains(i+"|"+j) || visited.contains(j+"|"+i)) ) {
                    degree[i]++;
                    degree[j]++;
                    visited.add(i+"|"+j);
                    visited.add(j+"|"+i);
                }
            }
        }

        System.out.println(Arrays.toString(degree));

        for(int i = 0; i < degree.length; i++) {
            if(degree[i]-1 == 0) {
                for (int j = 0; j < arr.length; j++) {
                    if(arr[i][j] == 1) {
                        System.out.println(i +" " + j +" is the critical connection");
                        return;
                    }
                }
            }
        }
    }
}