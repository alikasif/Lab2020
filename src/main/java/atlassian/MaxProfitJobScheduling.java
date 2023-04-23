package atlassian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ProfitWithIndex implements Comparable<ProfitWithIndex> {
    int i;
    int s;
    int e;
    int p;

    public ProfitWithIndex(int x, int y, int s, int e) {
        this.i = x;
        this.p = y;
        this.s = s;
        this.e =e;
    }

    @Override
    public int compareTo(ProfitWithIndex o) {
        System.out.println("comparing");
        return this.s - o.s;
    }

    @Override
    public String toString() {
        return this.i+"|"+ this.s+"|"+this.e+"|"+this.p;
    }
}
public class MaxProfitJobScheduling {

    public static void main(String[] args) {

        int[] start = {1, 2, 3, 4, 6};
        int[] end = {3, 5, 10, 6, 9};
        int[] profit = {20, 20, 100, 70, 60};

        List<ProfitWithIndex> profitWithIndices = new ArrayList<>();
        for(int i=0; i<profit.length; i++) {
            ProfitWithIndex profitWithIndex = new ProfitWithIndex(i, profit[i], start[i], end[i]);
            profitWithIndices.add(profitWithIndex);
        }

        Collections.sort(profitWithIndices);
        System.out.println(profitWithIndices);

        int totalProfit = 0;

        /*
        for(ProfitWithIndex profitWithIndex : profitWithIndices) {
            int i = profitWithIndex.i;
            if ()
        }*/
    }
}