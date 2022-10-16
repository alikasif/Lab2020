package leetcode;

import scala.Int;

import java.util.*;

public class LongestDistinctSubArray {

    public static void main(String[] args) {

        String [] arr = {"f", "s", "f", "e", "t", "w", "e", "n", "w", "e"};
        System.out.println(Arrays.toString(arr));
        //find(arr);
        find2(arr);
    }

    static void find2(String[] arr) {
        Map<String, Integer> map = new HashMap<>();
        Integer minStartIdx = 0;
        for(int i=0; i<arr.length; i++) {
            Integer oldIdx = map.put(arr[i], i);
            if(oldIdx != null) {
                minStartIdx = oldIdx+1;
            }
            System.out.println(Arrays.toString(Arrays.copyOfRange(arr, minStartIdx, i+1)));
        }
    }

    static void find(String[] arr){
        List<String> e = new ArrayList<>();
        int j = 0;

        for(int i=0; i<arr.length; i++) {
            if(!e.contains(arr[i])) {
                e.add(arr[i]);
                System.out.println(e.subList(j, e.size()));
            }
            else {
                e.add(arr[i]);
                while (!areAllUnique(e, j, e.size()))
                    j++;
            }
        }
    }

    static boolean areAllUnique(List<String> arr, int i, int j) {
        Set<String> s = new HashSet<>();
        for(int k = i; k<j; k++) {
            if(s.contains(arr.get(k)))
                return false;
            else
                s.add(arr.get(k));
        }
        return true;
    }
}
