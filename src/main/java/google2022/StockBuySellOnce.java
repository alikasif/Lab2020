package google2022;

public class StockBuySellOnce {

    public static void main(String[] args) {

        int[] prices = {1,1,5,3,6,7};

        int maxProfit = Integer.MIN_VALUE;

        for(int i=0;i< prices.length; i++) {
            for(int j=i+1; j<prices.length; j++) {
                if(prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }
        System.out.println(maxProfit);

        maxProfit = Integer.MIN_VALUE;
        int profit = Integer.MIN_VALUE;
        int cp = 0;
        int lb = prices[0];
        for(int i =1; i<prices.length; i++) {
            cp = Math.max(0, prices[i] - lb);
            maxProfit = Math.max(maxProfit, cp);
            lb = Math.min(lb, prices[i]);
        }
        System.out.println(maxProfit);
    }
}
