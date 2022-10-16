package leetcode;

import java.util.*;

class Pair implements Comparable<Pair> {
    int i; int v;

    public Pair(int x, int y) {
        v = x; i = y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                " v=" + v +
                ", i=" + i +
                '}';
    }

    @Override
    public int compareTo(Pair t1) {
        if(t1.v < v)
            return 1;
        if(t1.v > v)
            return -1;
        else
            return 0;
    }
}
public class MaxDistance {

    public static void main(String[] args) {

        int[] arr = {3, 5, 4, 2};
        // find i such that max(j-i) and a[i] <= a[j] => i<j
        int max_diff = -1;
        for(int i = 0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length; j++) {

                if(arr[i] <= arr[j])
                    max_diff = Math.max(max_diff, (j-i));
            }
        }
        System.out.println(max_diff);
        findMax(arr);
    }

    static void findMax(int[] arr) {

        List<Pair> pairs = new ArrayList<>();
        for(int i=0;i<arr.length;i++)
            pairs.add(new Pair(arr[i], i));

        Collections.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.v > p2.v)
                return 1;

                if(p1.v < p2.v)
                    return -1;

                return 0;
            }
        });
        System.out.println(pairs);

        int dp[] = new int[arr.length];
        dp[arr.length-1] = pairs.get(arr.length-1).i;

        for(int i = arr.length-2; i>=0; i--)
            dp[i] = Math.max(dp[i+1], pairs.get(i).i);

        System.out.println(Arrays.toString(dp));

        int ans = -1;
        for(int i=0; i <arr.length-1; i++)
            ans = Math.max(ans, dp[i+1] - pairs.get(i).i);

        System.out.println(ans);
    }
}