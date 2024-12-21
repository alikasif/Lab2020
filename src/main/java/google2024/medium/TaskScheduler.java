package google2024.medium;

import java.util.*;
import java.util.stream.Collectors;

public class TaskScheduler {
    public static void main(String[] args) {

        String[] tasks = {"A", "A", "A", "B", "B", "B"};
        //String[] tasks = {"A", "C", "A", "B", "D", "B"};
        int coolingTime = 3;

        /*
        *
        * we need to wait every n time before picking same task.
        * it means either we pick up another task or just idle and let time pass
        *
        * maintain a map of task with count. go over key set to see if there is task available to do
        * if yes then do else idle.
        *
        * */


        Map<String, Integer> map = new HashMap<>();
        map.put("A", 3);
        map.put("B", 3);

        List<Map.Entry<String, Integer>> collect = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        System.out.println(collect);

        // map.put("C", 1);
       // map.put("D", 1);

        Map<String, Integer> lastExecutionTime = new HashMap<>();
        Queue<String> stringQueue = new ArrayDeque<>();

        int k = map.keySet().size()+1;
        Set<String> keys = map.keySet();
        boolean found = false;

        while (true) {

/*            System.out.println("taskMap :: " +map);
            System.out.println("execMap :: "+ lastExecutionTime);*/
            List<String> taskList = new ArrayList<>();

            for(String key : keys) {
                if(map.get(key) > 0) {
                    found = true;
                    Integer lastExecTime = lastExecutionTime.getOrDefault(key, 0);

                    if( k - lastExecTime >= coolingTime ) {
                        stringQueue.add(key);
                        map.put(key, map.get(key)-1);
                        lastExecutionTime.put(key, k);
                        k++;
                        taskList.add(key);
                    }
                }
            }

            if (!found) {
                System.out.println(stringQueue);
                return;
            }

            found = false;
            while (taskList.size() - 1 < coolingTime) {
                stringQueue.add("idle");
                taskList.add("idle");
                k++;
            }

        }

    }
}
