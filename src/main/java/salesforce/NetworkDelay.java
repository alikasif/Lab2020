package salesforce;

import java.util.*;

public class NetworkDelay {
    public static void main(String[] args) {

        int[][] arr = {
                //{2,1,1},
                //{2,3,1},
                //{3,4,1}
                {1,2,1}
        };

        List<Integer> queue = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int k = 2;
        int delay = 0;
        queue.add(k);
        visited.add(k);

        while (!queue.isEmpty()) {

            List<Integer> tmpqueue = new ArrayList<>();
            Integer remove = queue.remove(queue.size() - 1);
            for(int i=0; i<arr.length; i++) {
                if(arr[i][0] == remove && !visited.contains(arr[i][1])) {
                    tmpqueue.add(arr[i][1]);
                }
            }
            if(!tmpqueue.isEmpty()) {
                delay++;
                queue.addAll(tmpqueue);
            }
        }
        System.out.println(delay);
    }
}
