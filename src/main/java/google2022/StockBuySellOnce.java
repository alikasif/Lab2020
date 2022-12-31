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
        int cp = 0;

        int cp2 = 0;
        int cp3 = 0;

        int lb = prices[0];
        for(int k = 1; k < prices.length; k++){
            cp = cp + Math.max(0, prices[k] - prices[k-1]); // unlimited txns allowed

            if ( (prices[k] - prices[k-1]) > 0 ) {
                if (cp3 == 0) {
                    cp3 = cp2;
                    cp2 =  prices[k] - prices[k - 1]; // unlimited txns allowed
                }
                else {
                    if ( (prices[k] - prices[k-1]) > cp2 ) {
                        if (cp3 < cp2)
                            cp3 = cp2;
                        cp2 = prices[k] - prices[k - 1]; // unlimited txns allowed
                    }
                }
            }

            if (prices[k] < lb)
                lb = prices[k];
            else {
                profit = prices[k] - lb;
            }
            maxProfit = Math.max(maxProfit, profit);
        }
        System.out.println(maxProfit);
        System.out.println(cp);
        System.out.println(cp2 +" "+ cp3);
    }
}
