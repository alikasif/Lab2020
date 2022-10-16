package google2022;

import java.util.HashMap;
import java.util.Map;

public class ThreeSum {
    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();
        int [] nums = {-1,0,1,2,-1,-4};

        for(int x : nums) {
            if( !map.containsKey(x) ) {
                map.put(x, 0-x);
            }
        }
        System.out.println(map);

        for(int i =0; i<nums.length; i++) {
            for(int j = i+1; j<nums.length; j++) {
                int k = 0 - (nums[i] + nums[j]);
                if (map.containsKey(k)) {
                    System.out.println(nums[i] +","+nums[j]+","+k);
                }
            }
        }
    }
}
