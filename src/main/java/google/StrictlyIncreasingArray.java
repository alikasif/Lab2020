package google;

public class StrictlyIncreasingArray {

    public static void main(String[] args) {
        int[] arr= {1, 1, 2};
        System.out.println(canBeIncreasing(arr));
    }

    public static boolean canBeIncreasing(int[] nums) {

        int index = 0;

        while( index < nums.length-1 && nums[index] < nums[index+1] )
            index++;

        return index == nums.length-1
                || canBe(nums, index+1, index-1)
                || canBe(nums, index+2, index);
    }

    static boolean canBe(int[] nums, int begin, int base) {

        int prevVal = base >= 0 ? nums[base] : Integer.MIN_VALUE;

        for(int i = begin; i < nums.length; i++) {
            if(nums[i] <= prevVal) {
                return false;
            }
        }

        for(int i = begin; i < nums.length-1; i++) {
            if(nums[i] >= nums[i+1]) {
                return false;
            }
        }
        return true;
    }
}
