package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaxSumLevelBT {

    public static void main(String[] args) {

        //Integer [] nodes = {1,7,0,7,-8, null, null};
        Integer [] nodes = {989,null,10250,98693,-89388,null,null,null,-32127};
        Map<Integer, Integer> levelSum  = new HashMap<>();

        int i = 0;
        while ( i < nodes.length ) {

            int elementsTConsider = Double.valueOf(Math.pow(2,i)).intValue();
            int s = 0;
            int j = 0;

            System.out.println("level : "+i+" elements to consider "+ elementsTConsider);

            while ( j < elementsTConsider ) {
                if( i+j < nodes.length && nodes[i+j] != null) {
                    s = s + nodes[i+j];
                }
                j++;
            }
            if( i+j >= nodes.length)
                break;

            levelSum.put(i, s);
            i++;
        }
        System.out.println(levelSum);
    }
}
