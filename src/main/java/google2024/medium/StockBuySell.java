package google2024.medium;

public class StockBuySell {

    static void buySellOnce(int[] arr) {
        int maxprofit = 0;
        int cp =0;
        int buy = arr[0];

        for(int i=1; i<arr.length; i++) {
            if(arr[i] < buy)
                buy = arr[i];

            if ( arr[i] - buy > cp)
                cp = arr[i] - buy;

            maxprofit = Math.max(maxprofit, cp);
        }
        System.out.println(maxprofit);
    }

    public int atMost2Transactions(int[] prices) {
        int oneBuyOneSell = 0;
        int twoBuyTwoSell = 0;
        int oneBuy = Integer.MAX_VALUE;
        int twoBuy = Integer.MAX_VALUE;

        for(int i = 0; i < prices.length; i++) {
            int p = prices[i];
            oneBuy = Math.min(oneBuy, p); // 1st buy cost
            oneBuyOneSell = Math.max(oneBuyOneSell, p - oneBuy);
            twoBuy = Math.min(twoBuy, p - oneBuyOneSell); // 2nd buy cost which is current price -
            twoBuyTwoSell = Math.max(twoBuyTwoSell, p - twoBuy);
        }
        return twoBuyTwoSell;
    }


    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        buySellOnce(arr);
    }
}
