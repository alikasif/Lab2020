package leetcode;

import java.util.*;

public class LongestConsecutiveSubSeq {

    public static void main(String[] args) {

        int[] arr = {1, 9, 3, 10, 4, 20, 2};

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        Set<Integer> set = new HashSet();
        Set<Integer> visited = new HashSet<>();

        for(int k : arr)
            set.add(k);

        int i = 0;

        while ( i < arr.length) {

            List<Integer> lst = new ArrayList<>();
            int e = arr[i];
            if(visited.contains(e)) {
                i++;
                continue;
            }
            int p = e-1;
            int n = e+1;
            lst.add(e);
            visited.add(e);
            while (true) {
                int ps = lst.size();

                if(set.contains(p)) {
                    lst.add(p);
                    visited.add(p);
                    p--;
                }
                if (set.contains(n)) {
                    lst.add(n);
                    visited.add(n);
                    n++;
                }
                if( ps == lst.size())
                    break;
            }
            System.out.println(lst);
            i++;
        }
    }
}
