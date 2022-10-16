package google;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public static void main(String[] args) {

        int[] arr = {3, 3, 4, 2, 4, 4, 2, 4, 4};

        double r = Math.floor(arr.length/2.0);
        System.out.println(r);

        Map<Integer, Integer> map = new HashMap<>();
        for(int k : arr) {
            map.put(k, map.get(k) == null?1: map.get(k)+1);
        }
        System.out.println(map);

        int maxe = -1;
        for(int k : map.keySet()) {
            if(map.get(k) > r)
                maxe = map.get(k);
        }
        System.out.println(maxe);
        mooreVoting(arr, r);
    }

    static void mooreVoting(int[] arr, double r) {

        int c = arr[0];
        int ci = 1;

        for(int i =1; i< arr.length; i++) {
            if(arr[i] == c) {
                ci++;
            }
            else{
                ci--;
            }
            if(ci ==0) {
                c = arr[i];
                ci =1;
            }
        }
        System.out.println(c +" "+ arr[ci]);
        ci =0;
        for(int h : arr) {
            if(h == c)
                ci++;
        }
        if(ci > r){
            System.out.println(c);
        }
    }
}
