package google2022;

import java.util.Arrays;

public class RankSort {

    public static void main(String[] args) {

        int[] arr = {40,10,20,30};

        Pair[] pairs = new Pair[arr.length];

        for(int i=0; i<arr.length; i++) {
            pairs[i] = new Pair(i, arr[i]);
        }

        Arrays.sort(pairs);

        int i = 0;
        for(Pair p : pairs) {
            arr[i] = p.i+1;
            i++;
        }
        System.out.println(Arrays.toString(arr));
    }
}
