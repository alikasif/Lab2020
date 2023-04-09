package uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Pair3 implements Comparable<Pair3>{
    int i;
    int w;
    public Pair3(int x, int y){
        i =x;
        w=y;
    }

    @Override
    public int compareTo(Pair3 o) {
        return this.i - o.i;
    }

    @Override
    public String toString() {
        return this.i+"|"+this.w;
    }
}
public class LargestMeteroite {
    public static void main(String[] args) {
        List<Pair3> lst = new ArrayList<>();
        lst.add(new Pair3(1,2));
        lst.add(new Pair3(2,3));
        lst.add(new Pair3(6,10));
        Collections.sort(lst);
        System.out.println(lst);

        Pair3 prev = lst.get(0);
        int maxw =  prev.w;

        for(int i=1; i<lst.size(); i++) {
            Pair3 c = lst.get(i);

            if(c.i <= prev.w && c.i >= prev.i) {
                maxw = maxw + c.w;
                prev =c;
            }
            else {
                if(c.w > maxw) {
                    maxw = Math.max(maxw, c.w);
                    prev = c;
                }
            }
        }
        System.out.println(maxw);
    }
}
