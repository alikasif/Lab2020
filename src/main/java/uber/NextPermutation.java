package uber;

import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {

         //int[] number = {2,5,6,7,4};
        //int[] number = {2,3,6,5,4};
        //int[] number = {1,2,3,4,5};
        int[] number = {4,3,2,1};

        System.out.println(Arrays.toString(number));

        // 2 3 1 4 5 => 2 3 1 5 4
        // 2 3 1 5 4 =>  2 3 4 5 1  => 2 3 4 1 5
        // 2 3 6 5 4 =>  2 4 6 5 3  => 2 4 5 3 6 => 2 4 3 6 5 => 2 4 3 5 6
        // 2 7 6 5 4 =>  4 7 6 5 2 => 4 2 5 6 7
        // 4 3 2 1 =>

        boolean found = false;
        int k = -1;

        for(int i=number.length-1; i>0 && !found; i--) {
            if(number[i-1] < number[i]) {
                k =i-1;
                found = true;
            }
        }
        System.out.println(k);

        if(found) {
            int x = -1;
            found = false;
            for (int i = k + 1; i < number.length && !found; i++) {
                if (number[i] > number[k]) {
                    x = i;
                    found = true;
                }
            }

            System.out.println(x);

            int z = number[k];
            number[k] = number[x];
            number[x] = z;

            System.out.println(Arrays.toString(number));

            int i = k + 1;
            int j = number.length - 1;
            if (found) {
                while (i < j) {
                    int f = number[i];
                    number[i] = number[j];
                    number[j] = f;
                    i++;
                    j--;
                }
            }
        }
        System.out.println(Arrays.toString(number));
    }
}
