package leetcode;

import java.util.*;

class MultiMap {
    int sum;
    List<Integer> elements;

    public MultiMap(int z){
        elements = new ArrayList<>();
        elements.add(z);
        sum = z;
    }

    public void addElement(int x) {
        sum = sum+x;
        elements.add(x);
    }
}

public class ZeroSumSubArray {

    public static void main(String[] args) {

        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};

        List<List<Integer>> lst = new ArrayList<>();
        List<MultiMap> multiMapList = new ArrayList<>();

        for(int i=0; i<arr.length; i++) {
            for(List<Integer> l : lst) {
                l.add(arr[i]);
                if(sum(l) == 0) {
//                    System.out.println("zero sum "+ l);
                }
            }
            List<Integer> tmp = new ArrayList<>();
            tmp.add(arr[i]);
            lst.add(tmp);

            {
                for(MultiMap m: multiMapList) {
                    m.addElement(arr[i]);
                    if(m.sum == 0)
                        System.out.println(m.elements);
                }
                multiMapList.add(new MultiMap(arr[i]));
            }

        }


        }

    private static int sum(List<Integer> t) {
        int s=0;
        for(int k : t)
            s+=k;
        return s;
    }
}