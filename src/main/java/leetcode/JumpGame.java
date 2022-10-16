package leetcode;

import java.util.*;

public class JumpGame {

    public static void main(String[] args) {

        List<Integer> stack = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

//        int[] arr = {2,3,1,1,4};
//        map.put(0,2); map.put(1,3); map.put(2,1); map.put(3,1); map.put(4,4);

        int[] arr = {3,2,1,0,4};
        map.put(0,3); map.put(1,2); map.put(2,1); map.put(3,0); map.put(4,4);

        stack.add(0);
        jump(arr, stack, map);
    }

    static void jump(int[] arr, List<Integer> stack, Map<Integer, Integer> map ) {

        System.out.println(map+"  stack -> " + stack);

        Integer e = stack.get(stack.size()-1);

        if (e == null || map.size() == 0 || map.get(e) == null)
            return;

        int v = map.get(e);

        if (map.get(e)-1 > 0) {
            map.put(e, map.get(e)-1);
        }
        else {
            map.remove(e);
            stack.remove(e);
        }

        if ( v == arr.length-1) {
            System.out.println("reached end...");

     //       return;
        }
        else if  ( v+e < arr.length ) {
            stack.add(v+e);
        }
        else {
            ;
        }
        jump(arr, stack, map);
    }

}
