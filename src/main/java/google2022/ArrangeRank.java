package google2022;

import java.util.Arrays;

class Pair implements Comparable<Pair>{
    int v;
    int i;
    public Pair(int j, int k) {
        this.i = j;
        this.v = k;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "v=" + v +
                ", i=" + i +
                '}';
    }

    @Override
    public int compareTo(Pair o) {
        return o.v > this.v? 1 : -1;
    }
}

public class ArrangeRank {
    public static void main(String[] args) {

        int[] score = {10,3,8,9,4};
        Pair[] scoreindex = new Pair[5];
        int i = 0;

        for(int x : score) {
            scoreindex[i] = new Pair(i, x);
            i++;
        }

        Arrays.sort(scoreindex);

        System.out.println(Arrays.toString(scoreindex));
    }
}
