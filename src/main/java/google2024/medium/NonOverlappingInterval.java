package google2024.medium;

import java.util.*;

class c1 implements Comparator<int[]> {
    @Override
    public int compare(int[] o1, int[] o2) {

        if (o1[0] == o2[0])
            return 0;

        if ( o2[0] - o1[1] == 0)
            return -1;

        return 1;
    }
}

public class NonOverlappingInterval {
    public static void main(String[] args) {

        int[][] arr = { {1,2}, {1, 3}, {3,4},{2,3} };
        List<int[]> list = new ArrayList<>();

        for(int[] arr1 : arr) {
            list.add(arr1);
        }

        Collections.sort(list, new c1());

        int end = list.get(0)[1];
        int count =1 ;

        for(int i=1; i< list.size(); i++) {
            int[] tmp = list.get(i);
            if(tmp[0] >= end) {
                end = tmp[1];
                count++;
            }
        }

        System.out.println(list.size() - count);

        for(int[] x : list) {
            System.out.print(Arrays.toString(x));
            System.out.print(" -> ");
        }
    }
}

