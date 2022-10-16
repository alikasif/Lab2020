package google2022;

import java.util.*;

public class RelativeSort {

    public static void main(String[] args) {

/*
        int[] arr1= {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
*/
        int[] arr1 = {28,6,22,8,44,17};
        int[] arr2 = {22,28,8,6};

        Map<Integer, Integer> map = new HashMap<>();
        for(int x : arr1) {
            Integer c = map.get(x);
            if(c == null)
                map.put(x,1);
            else
                map.put(x, c+1);
        }
        int i = 0;
        for(int x : arr2) {
            int c = map.get(x);
            c = c+i;
            while (i < c) {
                arr1[i] = x;
                i++;
            }
            map.remove(x);
        }
        System.out.println(Arrays.toString(arr1));
        ArrayList<Integer> list = new ArrayList<Integer>(map.keySet());

        Collections.sort(list);
        int j = 0;
        while (i < arr1.length){
            arr1[i]= list.get(j);
            j++;
            i++;
        }
        System.out.println(Arrays.toString(arr1));
    }
}