package google2022;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestConsecSeq {

    public static void main(String[] args) {

        int [] arr = {0,3,7,2,5,8,4,6,0,1};

        Set<Integer> set = new HashSet<>();
        for(int x: arr)
            set.add(x);

        int ls =0;

        for(int x : arr) {
            if(!set.contains(x-1)) {
                int c = 1;
                //int t = x;
                while (set.contains(x+1)) {
                    c++;
                    x++;
                }
                ls = Math.max(ls, c);
            }
        }
        System.out.println(ls);
    }
}
