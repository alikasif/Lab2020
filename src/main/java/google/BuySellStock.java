package google;

public class BuySellStock {

    public static void main(String[] args) {

//        buySellOnce(new double[]{310, 315, 275, 295, 260, 270, 290, 230, 255, 250});
        buySellTwice(new int[]{310, 315, 275, 295, 260, 270, 290, 230, 255, 250});
    }

    static void buySellOnce(double[] prices) {

        double minPrice = Double.MAX_VALUE , maxProfit = 0.0;

        double mp1=0.0, mp2=0.0;

        for (double price : prices) {
            minPrice = Math.min(minPrice , price);
            maxProfit = Math.max(maxProfit , (price - minPrice));

        }

        System.out.println(maxProfit+" "+minPrice);
        int i=0;
        minPrice = prices[i];
        maxProfit = 0.0;

        while (++i < prices.length) {
            maxProfit = Math.max(maxProfit, (prices[i]-minPrice) );
            minPrice = Math.min(minPrice, prices[i]);
            System.out.println(maxProfit);
            if(mp1 < maxProfit) {
                mp2 = mp1;
                mp1 = maxProfit;
            }
        }
        System.out.println(maxProfit +" "+minPrice);
        System.out.println(mp1 +" "+mp2);
    }

    static void buySellTwice(int[] prices) {

        int buy1, profit1, buy2, profit2;
        buy1 = buy2 = Integer.MAX_VALUE;
        profit1 = profit2 = 0;

        for (int i = 0; i < prices.length; i++) {

            buy1 = Math.min(buy1, prices[i]);
            profit1 = Math.max(profit1, prices[i] - buy1);

            System.out.println(buy2 +"  " + (prices[i]- profit1));

            buy2 = Math.min(buy2, prices[i] - profit1);
            profit2 = Math.max(profit2, prices[i] - buy2);

        }
        System.out.println(profit2 );
    }
}