package uber;

import java.util.*;

class Pair implements Comparable<Pair>{

    int i;
    int v;

    public Pair(int x, int y) {
        i =x;
        v=y;
    }

    @Override
    public int compareTo(Pair o) {
        if(o.v < this.v)
            return -1;
        return 1;
    }

    @Override
    public String toString() {
        return "("+i+","+v+")";
    }
}

public class HouseRobber {
    public static void main(String[] args) {

        int[] arr = {2,3,5};

        List<Pair>  pairs = new ArrayList<>();
        for(int i=0;i<arr.length;i++) {
            pairs.add(new Pair(i, arr[i]));
        }

        Collections.sort(pairs);
        System.out.println(pairs);
        int amount = 0;

        Set<Integer> nonSelectableIndices = new HashSet<>();

        for(int i=0; i<pairs.size(); i++) {
            Pair p = pairs.get(i);
            if (!nonSelectableIndices.contains(p.i)) {
                amount = amount + p.v;
                nonSelectableIndices.add(p.i - 1);
                nonSelectableIndices.add(p.i + 1);
                if(p.i==0) {
                    nonSelectableIndices.add(arr.length-1);
                }
                if(p.i==arr.length-1) {
                    nonSelectableIndices.add(0);
                }

            }
        }
            System.out.println(amount);
    }
}
