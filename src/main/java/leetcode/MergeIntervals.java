package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {

        Pair p1 = new Pair(-4, -1);
        Pair p2 = new Pair(0, 2);
        Pair p3 = new Pair(3, 6);
        Pair p4 = new Pair(7, 9);
        Pair p41 = new Pair(9, 10);
        Pair p5 = new Pair(11, 12);
        Pair p6 = new Pair(14, 17);
        List<Pair> pairs = new ArrayList<>();
        pairs.add(p1);
        pairs.add(p2);
        pairs.add(p3);
        pairs.add(p4);
        pairs.add(p41);
        pairs.add(p5);
        pairs.add(p6);

        Pair p = new Pair(1, 8);

        int i = 0;
        boolean b = true;

        while (b) {

            System.out.println(pairs);
            Pair t = pairs.get(i);

            if(p == null)
                p = pairs.get(i+1);

            int x = -1;
            int y = -1;

            if(isINtersect(p, t)) {
                if(p.v < t.v)
                    x = p.v;
                else
                    x = t.v;

                if( p.i > t.i)
                    y = p.i;
                else
                    y = t.i;
                pairs.remove(t);
                pairs.remove(p);
                p = null;
                pairs.add(i, new Pair(x, y));
            }
            else
                i++;

            if(i == pairs.size()-1)
                b=false;
        }
        System.out.println(pairs);
    }

    static boolean isINtersect(Pair p1, Pair p2) {

        if( (p1.v >= p2.v && p1.v <= p2.i) || (p1.i >= p2.v && p1.i <= p2.i) )
            return true;
        if( (p2.v >= p1.v && p2.v <= p1.i) || (p2.i >= p1.v && p2.i <= p1.i) )
            return true;
        return false;
    }
}
