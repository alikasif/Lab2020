package google2022;

import java.util.Arrays;

public class LIS
{
    public static void main(String[] args) {

        int[] nums = {4,5,6,3,7,1,2};

        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size)
                ++size;
            System.out.println(x +" "+ Arrays.toString(tails));
        }
        System.out.println(size);
    }
}
