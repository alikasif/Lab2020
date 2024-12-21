package google2024.hard;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSeq {

    public static void main(String[] args) {

        int [] arr = {100, 4, 200, 1, 3, 2};
   //     int [] arr = {0,3,7,2,5,8,4,6,0,1};
        int maxL = 0;
        Set<Integer> set = new HashSet<>();

        for(int x : arr)
            set.add(x);

        Set<Integer> visited = new HashSet<>();

        for(int x : arr) {

            if (set.contains(x-1))
                continue;
            System.out.println();
            while (true) {
                System.out.print(x + " => ");
                if(set.contains(x+1))
                    x++;
                else
                    break;
            }
        }
    }
}
