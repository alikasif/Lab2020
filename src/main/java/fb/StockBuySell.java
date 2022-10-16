package fb;

import java.util.Arrays;

public class StockBuySell {

    public static void main(String[] args) {

        //int[] arr = {100, 180, 260, 310, 40, 535, 695};
        //int [] arr = {4,2,2,2,4};
       int[] arr= {7,1,5,3,6,4};

        int [] rMax = new int[arr.length];
        rMax[arr.length-1] = arr[arr.length-1];

        for ( int i = arr.length-2; i >=0; i-- ) {
            rMax[i] = Math.max(arr[i], rMax[i+1]);
        }

        int [] lMin = new int[arr.length];

        int profit = 0;
        lMin[0] = arr[0];

        for ( int i = 1; i < arr.length; i++ ) {
            lMin[i] = Math.min(arr[i], lMin[i-1]);
            System.out.println(arr[i] +" "+lMin[i]);
            int t = profit;
            profit = arr[i]-lMin[i];

            if ( profit < t) {
                System.out.println(" profit: "+ t);
                profit = 0;
                lMin[i] = arr[i];
            }
            if ( i == arr.length-1)
                System.out.println(" profit: "+ profit);
        }

        /*System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(lMin));
        System.out.println(Arrays.toString(rMax));

        for(int i = 0; i < arr.length; i++) {
            int t = profit;
            profit = arr[i]-lMin[i];
            if ( profit < t ) {
                System.out.println(t);
                profit = 0;
            }
        }
        System.out.println(profit);*/
    }
}
