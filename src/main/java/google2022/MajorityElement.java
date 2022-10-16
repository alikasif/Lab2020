package google2022;

public class MajorityElement {

    public static void main(String[] args) {

        int[] nums = {2,2,1,1,1,2,2};

        int x = nums[0];
        int c = 1;

        for(int i =1; i< nums.length; i++) {

            if( x == nums[i])
                c++;
            else
                c--;

            if ( c ==0) {
                x = nums[i];
                c=1;
            }

        }
        System.out.println(x +" "+c);
    }
}