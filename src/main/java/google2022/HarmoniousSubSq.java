package google2022;

import java.util.Arrays;

public class HarmoniousSubSq {

    public static void main(String[] args) {

        int[] nums = {1,3,2,2,5,2,3,7};
        Arrays.sort(nums);

        int s=0;
        for(int i =1; i< nums.length; i++) {

            if(nums[i] - nums[s] == 1){
                continue;
            }
            else {
                System.out.println("window "+ s+" "+(i-1));
                while (s < i) {
                    if (nums[i] - nums[s] == 1) {
                        break;
                    }
                    else{
                        s++;
                    }
                }
            }
        }
    }
}
