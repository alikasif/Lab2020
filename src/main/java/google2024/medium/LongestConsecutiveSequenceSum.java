package google2024.medium;

import java.util.*;

public class LongestConsecutiveSequenceSum {
    public static void main(String[] args) {

        int[] arr = {100, 4, 200, 1, 3, 2};
        //int[] arr = {0,3,7,2,5,8,4,6,0,1};

        Set<Integer> set = new HashSet<>();
        Arrays.stream(arr).forEach(x-> set.add(x));
        System.out.println(set);
        int max = 0;
        for(int x : set) {
            int c = 0;
            if(!set.contains(x-1)) {
                c++;
                int y = x+1;
                while (set.contains(y)){
                    c++;
                    y++;
                }
                max = Math.max(max, c);
            }
        }
        System.out.println(max);
    }
}
