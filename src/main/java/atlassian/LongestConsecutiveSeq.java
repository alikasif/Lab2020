package atlassian;

import java.util.*;

public class LongestConsecutiveSeq {
    public static void main(String[] args) {

        int[] arr = {9,5,4,9,10,10,6};
        // sort nlogn
        //

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i=0; i<arr.length; i++) {

            List<Integer> lst =  map.get(arr[i]);

            if (lst == null) {
                lst = new ArrayList<>();
                lst.add(arr[i]);
                map.put(arr[i]-1, lst);
                map.put(arr[i]+1, lst);
            }
            else {
                lst.add(arr[i]);
                map.put(arr[i]-1, lst);
                map.put(arr[i]+1, lst);
            }
            System.out.println(arr[i] +" => " + map);
        }
        System.out.println(map);
        System.out.println();
        newWay(arr);
    }

    static int[] newWay(int[] arr) {

        Set<Integer> set = new HashSet<>();

        for(int x : arr)
            set.add(x);

        int c =0;
        int r=0;

        for(Integer x : set) {
            if(!set.contains(x-1)) {
                int y = x+1;
                while (set.contains(y)) {
                    r++;
                    y+=1;
                }
                c = Math.max(c, (y-x));
            }
        }
        System.out.println(c);
        System.out.println(r);
        return arr;
    }
}
