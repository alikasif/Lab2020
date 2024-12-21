package google2024.medium;

import java.util.Arrays;

public class FirstLastPosInArray {

    public static void main(String[] args) {
        int[] arr = {5,7,7,8,8,10};
        int target = 8;
        searchRange(arr, target);
    }

    static public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        System.out.println(Arrays.toString(result));
        return result;
    }

    static public int findFirst(int[] nums, int target){

        int result = -1;
        int low = 0;
        int high = nums.length - 1;

        while(low <= high){
            int mid = low + ((high-low)/2);

            if (target > nums[mid] ){
                low = mid +1;
            } else if ( target < nums[mid] ) {
                high = mid - 1;
            } else { // nums[mid] == target
                result = mid;

                // because nothing after mid
                // can be the first occurance of target.
                //maybe mid is the first occurance , maybe not
                //so let's narrow the target for [0...mid-1] and find out
                high = mid - 1;
            }
        }
        return result;
    }

    static public int findLast(int[] nums, int target){

        int result = -1;
        int low = 0;
        int high = nums.length - 1;

        while(low <= high){

            int mid = low + (high-low)/2;

            if (target > nums[mid] ){
                low = mid +1;
            } else if ( target < nums[mid] ){
                high = mid - 1;
            } else { // nums[mid] == target
                result = mid;
                // because nothing before mid
                // can be the last occurance of target.
                //maybe mid is the last occurance , maybe not
                //so let's narrow the target for [mid+1...high] and find                   // out
                low = mid + 1;

            }
        }

        return result;
    }
}