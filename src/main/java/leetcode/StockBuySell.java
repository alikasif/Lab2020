package leetcode;

public class StockBuySell {

    public static void main(String[] args) {
        //int[] price = {310,315, 275, 295, 260, 270, 290, 230, 255, 250};

        int[] price = {12,11,13,9,12,8,14,13,15};
        int proft = Integer.MIN_VALUE;
        int minP = Integer.MAX_VALUE;
        for(int i =0; i < price.length; i++) {
            for(int j= i; j < price.length; j++) {
                if(price[j] > price[i]){
                    proft = Math.max(proft, price[j] - price[i]);
                }
            }
        }
        System.out.println(proft);

        // 2 transactions
        proft = Integer.MIN_VALUE;
        int pp = -1;
        minP = Integer.MAX_VALUE;
        for(int i =0; i < price.length; i++) {
            minP = Math.min(minP, price[i]);
            int tmp = Math.max(proft, price[i]- minP);
            if(tmp > proft){
                pp = proft;
                proft = tmp;
            }
        }
        System.out.println(proft);
        System.out.println(pp);


    }
}
