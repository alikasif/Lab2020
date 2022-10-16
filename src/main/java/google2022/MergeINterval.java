package google2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeINterval {

    public static void main(String[] args) {

        int [][] arr =  {
                {1,8},
                {2,6},
                {8,10},
                {10,18}};
        List<int[]> lst = new ArrayList<>();

        for(int i=0; i <arr.length; i++) {
            lst.add(arr[i]);
        }

        int i = 0;

        while (i < lst.size()-1) {

            int[] r = merge(lst.get(i), lst.get(i+1));

            System.out.println(Arrays.toString(r));

            if (r != null) {
                lst.remove(i);
                lst.add(i, r);
                lst.remove(i+1);
            }
            else{
                i++;
            }
            System.out.println(lst);
        }
        for(int j=0;j<lst.size();j++) {
            System.out.println(Arrays.toString(lst.get(j)));
        }
    }

    static int[] merge(int[] x, int[] y) {
        int a=-1,b=-1;

        if ( y[0] > x[1]) {
            return null;
        }

        if(x[0] <= y[0])
            a = x[0];
        else
            a =x[0];

        if (x[1] >= y[1])
            b= x[1];
        else
            b= y[1];

        return new int[]{a,b};
    }
}
