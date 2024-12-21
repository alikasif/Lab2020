package google2024.medium;

import java.util.*;

public class Sum3 {

    public static void main(String[] args) {

        int[] arr = {-1, 0, 1, 2, -1, -4};

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i =0; i<arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            List<Integer> integers = map.get(arr[i]);
            integers.add(i);
        }
        System.out.println(map);

        for(Integer i=0; i < arr.length; i++) {
            for(Integer j=0; j < arr.length; j++) {
                if (i != j) {
                    int lookingFor = -(arr[i] + arr[j]);
                    List<Integer> integers = map.get(lookingFor);
                    ArrayList<Integer> objects = new ArrayList<>();
                    if (integers != null) {
                        objects.addAll(integers);
                        System.out.println(objects);
                        objects.remove(i);
                        objects.remove(j);
                        if (!objects.isEmpty()) {
                            System.out.println(i+"|"+j+"|"+objects);
                        }
                    }
                }
            }
        }
    }
}
