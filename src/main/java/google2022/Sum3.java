package google2022;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sum3 {

    public static void main(String[] args) {

        int[] arr = {-1,0,1,2,-1,-4};

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0; i< arr.length; i++) {
            for (int  j = 0; j< arr.length; j++) {
                if( i != j) {
                    int s = arr[i] + arr[j];
                    map.put(s, List.of(i, j));
                }
            }
        }

        System.out.println(map);

        for(int i=0; i<arr.length; i++) {
            for(Integer k : map.keySet()) {
                if (k + arr[i] == 0 && !map.get(k).contains(i)) {
                    System.out.println(i + " " + map.get(k));
                }
            }
        }
    }
}