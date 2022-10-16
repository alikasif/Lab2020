package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KDiffPairs {

    public static void main(String[] args) {

//        int[] arr = {1, 3, 4, 1, 5};
//        int k = 0;

        int[] arr = {1,2,4,4,3,3,0,9,2,3};
        int k = 3;
        Map<Integer, Integer> map = new HashMap<>();

        for(int x : arr) {
            map.put(x,x);
        }
        Arrays.sort(arr);
        for(int x : arr) {
            if(map.get(x+k) != null) {
                System.out.println(x+" "+map.get(x+k));
            }
        }
    }
}
