package google;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Pair {
    int f;
    int s;

    public Pair(int i, int j){
        f = i;
        s = j;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "f=" + f +
                ", s=" + s +
                '}';
    }
}

public class InsertAndMergeInterval {

    public static void main(String[] args) {


        //Pair n = new Pair(2,5);
        //Pair n = new Pair(4,5);
        //Pair n = new Pair(11, 15);
        //Pair n = new Pair(0,10);
        Pair n = new Pair(0,1);
        //Pair n = new Pair(4,9);

        List<Pair> lst = new ArrayList<>();
        lst.add(new Pair(1, 3));
        lst.add(new Pair(6, 9));
/*
        lst.add(new Pair(1,2));
        lst.add(new Pair(3,5));
        lst.add(new Pair(6,7));
        lst.add(new Pair(8, 10));
        lst.add(new Pair(12,16));
*/

        boolean found = false;
        int i = 0;

        if (n.s < lst.get(0).f)
            lst.add(0, n);

        else if (lst.get(lst.size() - 1).s < n.f)
            lst.add(n);

        else if (n.f < lst.get(0).f && n.s > lst.get(lst.size() - 1).s) {
            lst.clear();
            lst.add(n);
        } else {
            Stack<Pair> stack = new Stack<>();
            stack.add(lst.get(0));
            lst.remove(0);
            System.out.println(lst);
            i = 0;

            while (i < lst.size()) {

                if (n == null) {
                    n = lst.get(i);
                    i++;
                }
                Pair p = stack.peek();
                System.out.println(stack + " " + n + " " + i);

                if (n.f > p.s) {
                    stack.add(n);
                    n = null;
                } else if (n.f <= p.f && p.s >= n.s) {
                    Pair t = new Pair(n.f, p.s);
                    stack.pop();
                    stack.add(t);
                    n = null;
                } else if (p.f <= n.f && p.s >= n.s) {
                    Pair t = new Pair(p.f, p.s);
                    stack.pop();
                    stack.add(t);
                    n = null;
                } else if (n.f <= p.f && n.s >= p.s) {
                    stack.pop();
                    stack.add(n);
                    n = null;
                } else if (n.f >= p.f && p.s <= n.s) {
                    Pair t = new Pair(p.f, n.s);
                    stack.pop();
                    stack.add(t);
                    n = null;
                }
            }
            System.out.println(stack);
        }


        System.out.println(" lst => "+ lst);
    }
}
