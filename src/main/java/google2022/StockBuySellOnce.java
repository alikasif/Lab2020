package google2022;

public class StockBuySellOnce {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};

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

        int lb = prices[0];
        for(int k =1; k< prices.length; k++){
            if (prices[k] < lb)
                lb = prices[k];
            else {
                profit = prices[k] - lb;
            }
            maxProfit = Math.max(maxProfit, profit);
        }
        System.out.println(maxProfit);
    }
}
