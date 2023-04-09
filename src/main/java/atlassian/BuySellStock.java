package atlassian;

import java.util.Arrays;

public class BuySellStock {
    public static void main(String[] args) {
        int[] stock = {3,1,5,4,7};
        // buySellOnce(stock);
        // buySellUnlimited(stock);
        // buySellTwice(stock);
        buySellKTimes(stock, 3);
    }

    static void buySellKTimes(int[] stocks, int k) {

        int[] lprofit = new int[stocks.length];
        int min = stocks[0];
        lprofit[0] =0;
        for(int i=1; i<stocks.length; i++) {
            lprofit[i] = Math.max(lprofit[i-1], stocks[i] - min);
            min = Math.min(min, stocks[i]);
        }
        System.out.println(Arrays.toString(lprofit));

        int[] rprofit = new int[stocks.length];
        int max = stocks[stocks.length-1];
        rprofit[stocks.length-1] = 0;

        for(int i=stocks.length-2; i>=0; i--) {
            rprofit[i] = Math.max(rprofit[i+1], max-stocks[i]);
            max = Math.max(max, stocks[i]);
        }
        System.out.println(Arrays.toString(rprofit));

        int mp=0;
        for(int i=0; i< lprofit.length; i++) {
            mp = Math.max(mp, lprofit[i] + rprofit[i]);
        }
        System.out.println(mp);

    }

    static void buySellTwice(int[] stocks) {

        int[] lprofit = new int[stocks.length];
        int min = stocks[0];
        lprofit[0] =0;
        for(int i=1; i<stocks.length; i++) {
            lprofit[i] = Math.max(lprofit[i-1], stocks[i] - min);
            min = Math.min(min, stocks[i]);
        }
        System.out.println(Arrays.toString(lprofit));

        int[] rprofit = new int[stocks.length];
        int max = stocks[stocks.length-1];
        rprofit[stocks.length-1] = 0;

        for(int i=stocks.length-2; i>=0; i--) {
            rprofit[i] = Math.max(rprofit[i+1], max-stocks[i]);
            max = Math.max(max, stocks[i]);
        }
        System.out.println(Arrays.toString(rprofit));

        int mp=0;
        for(int i=0; i< lprofit.length; i++) {
            mp = Math.max(mp, lprofit[i] + rprofit[i]);
        }
        System.out.println(mp);

    }

    static void buySellUnlimited(int[] stocks) {

        int profit = 0;

        for(int i=1; i<stocks.length; i++) {
            if(stocks[i] > stocks[i-1]) {
                profit = profit + stocks[i] - stocks[i-1];
            }
        }
        System.out.println(profit);
    }

    static void buySellOnce(int[] stocks) {

        int profit = 0;
        int min = stocks[0];

        for(int i=1; i<stocks.length; i++) {
            profit = Math.max(profit, stocks[i] - min);
            min = Math.min(min, stocks[i]);
        }
        System.out.println(min +" " + profit);
    }
}
