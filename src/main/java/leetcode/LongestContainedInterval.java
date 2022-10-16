package leetcode;

import java.util.*;

public class LongestContainedInterval {
    public static void main(String[] args) {

        int[] arr = {3, -2, 7, 9, 8, 1, 2, 0, -1, 5, 8};

//        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<arr.length;i++)
            map.put(arr[i], i);

        for(int i = 0; i<arr.length; i++) {
            List<Integer> l = new ArrayList<>();
            l.add(arr[i]);
            int k = arr[i];
            int j = arr[i];
            boolean contains = true;
            k++;
            j--;
            map.remove(arr[i]);
            while (contains) {
                if(map.containsKey(k)) {
                    l.add(k);
                    map.remove(k);
                    k++;
                }
                if(map.containsKey(j)) {
                    l.add(j);
                    map.remove(j);
                    j--;
                }
                else if( !map.containsKey(k) && !map.containsKey(j)) {
                    contains = false;
                }
            }
            System.out.println(l);
        }
    }
}
