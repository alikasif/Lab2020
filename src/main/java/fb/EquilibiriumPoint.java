package fb;

import java.util.Arrays;

public class EquilibiriumPoint {

    public static void main(String[] args) {

        //int[] arr = {1,3,5,2,2};
        int[] arr = {1,3,5,2,9};

        int sum = 0;
        for(int i : arr)
            sum +=i;

        int e = sum/2;
        sum=0;
        for(int i =0; i < arr.length; i++) {
            sum+=arr[i];

            if (sum > e) {
                System.out.println(i);
                break;
            }

            if ( sum == e) {
                System.out.println(i);
                break;
            }
        }
    }
}
