package google2022;

import java.util.*;

public class CombinationSum {

    public static void main(String[] args) {
        int[] arr = {2,3,5};
        int tgt = 8;
        cs(arr, tgt, new ArrayList<>());
    }

    static void cs(int[] arr, int t, List<Integer> res){

        for(int i=0;i<arr.length;i++) {
            List<Integer> tmp = new LinkedList<>();
            tmp.addAll(res);
            tmp.add(arr[i]);
            int st  = sum(tmp);
            if ( st >= t) {
                if (st == t)
                    System.out.println(tmp);
                return;
            }
            else {
                if( st < t) {
                    cs(arr, t, tmp);
                }
            }
        }
    }

    private static int sum(List<Integer> res) {
        int r=0;
        for(int t: res)
            r =r+t;
        //System.out.println(r);
        return r;
    }
}
