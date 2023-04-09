package uber;

import java.util.ArrayList;
import java.util.List;

public class SubArrayEq2Sum {
    public static void main(String[] args) {

        int[] arr = {2,5,6};

        int s = 10;
        int c=0;
        int cs=arr[0];
        int j=0;
        List<Integer> lst = new ArrayList<>();
        if(cs < s) {
            c++;
            lst.add(cs);
        }

        for(int i=1; i< arr.length; i++) {

            if(arr[i] < s) {
                lst.add(arr[i]);
                c++;
            }

            cs = cs + arr[i];
            if(cs < s) {
                c++;
                lst.add(cs);
            }
            else {
                j++;
                cs = arr[j];
                while (++j < i) {
                  cs = cs + arr[j];
                  if(cs < s) {
                      c++;
                      lst.add(cs);
                  }
                  else {
                      cs=0;
                  }
                  j++;
                }
            }
        }
        System.out.println(c);
        System.out.println(lst);
    }
}
