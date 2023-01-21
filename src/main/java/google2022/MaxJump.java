package google2022;

public class MaxJump {
    public static void main(String[] args) {
        int[] arr = {2,5,1,0,0,0,1};

        int j =0;
        for (int i=0;i<arr.length; i++) {
            if( j < i)
                break;
            j = Math.max(j, i + arr[i]);
            if(j >= arr.length)
                System.out.println("reached");
        }
        System.out.println(j);
        System.out.println(canJump(arr));
    }
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int reachable = 0;
        for(int i=0; i<n; i++){
            if(reachable < i) return false;
            reachable = Math.max(reachable, i+nums[i]);
        }
        return true;
    }
}
