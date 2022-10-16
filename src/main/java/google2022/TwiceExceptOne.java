package google2022;

public class TwiceExceptOne {

    public static void main(String[] args) {
        
        int[] nums = {4,1,2,1,2};
        int r = nums[0];

        for(int i =1; i< nums.length; i++) {
            r = (r ^ nums[i]);
        }
        System.out.println(r);
    }
}
