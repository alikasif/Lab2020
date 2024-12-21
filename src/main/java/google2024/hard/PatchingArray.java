package google2024.hard;

public class PatchingArray {

    static public void minPatches ( int[] nums, int n){
        long miss = 1;
        int result = 0;
        int i = 0;

        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss = miss + nums[i];
                i++;
            }
            else {
                miss = 2 * miss;
                result++;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {1,5,10};
        minPatches(arr, 20);
    }
}
