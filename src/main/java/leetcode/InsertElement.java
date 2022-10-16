package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertElement {

    public static void main(String[] args) {

        List<int[]> lst= new ArrayList<>();
        lst.add(new int [] {1, 2});
        lst.add(new int [] {3, 5});
        lst.add(new int [] {4, 8});
        lst.add(new int [] {6, 7});
        lst.add(new int [] {8, 10});
        lst.add(new int [] {12, 16});

        /*
            1,2   1,4  - > 1,4
            1,2   2,4  ->  1,4
            1,2   3,4  -> 1,2 3,4
            1,5   1,4  -> 1,5
        */

        int i = 0;

        while (i < lst.size()-1) {
            System.out.println(i);
            print(lst);
            if (lst.get(i + 1)[0] <= lst.get(i)[1] && lst.get(i + 1)[1] >= lst.get(i)[1]) {
                int[] tmp = {lst.get(i)[0], lst.get(i + 1)[1]};
                lst.set(i, tmp);
                lst.remove(i + 1);
                //i-=1;
            }
            else if (lst.get(i + 1)[0] <= lst.get(i)[1] && lst.get(i)[1] >= lst.get(i + 1)[0]) {
                lst.remove(i + 1);
               // i-=1;
            }
            else if (lst.get(i + 1)[0] <= lst.get(i)[1] &&  lst.get(i + 1)[1] <= lst.get(i)[1] ) {
                lst.remove(i + 1);
             //   i-=1;
            }
            else {
                i++;
            }

            print(lst);
        }


    }

    static void print(List<int[]> lst) {
        System.out.print("lst => ");
        for(int i = 0; i< lst.size(); i++) {
            System.out.print(Arrays.toString(lst.get(i)));
            System.out.print(" , ");
        }
        System.out.println();
    }
}
