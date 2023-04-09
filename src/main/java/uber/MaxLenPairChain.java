package uber;

import java.util.*;

class Pair2 implements Comparable<Pair2>{
    int a;
    int b;

    public Pair2(int i, int j) {
        a=i;
        b=j;
    }

    @Override
    public int compareTo(Pair2 o) {
        if(this.a < o.a)
            return -1;
        else
            return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return this.a == ((Pair2)obj).a && this.b == ((Pair2)obj).b;
    }

    @Override
    public String toString() {
        return this.a +"|"+this.b;
    }
}
public class MaxLenPairChain {
    public static void main(String[] args) {
        List<Pair2> lst = new ArrayList<>();
        lst.add(new Pair2(4,6));
        lst.add(new Pair2(2,3));
        lst.add(new Pair2(9,12));
        lst.add(new Pair2(15,20));
        Collections.sort(lst);
        System.out.println(lst);
        Set<Pair2> chain = new HashSet<>();

        Pair2 prev = lst.get(0);
        chain.add(prev);

        for(int i=1; i<lst.size(); i++) {

                Pair2 c = lst.get(i);

                if (prev.b < c.a) {
                    chain.add(c);
                    prev = c;
                }
            }
        System.out.println(chain);
    }
}