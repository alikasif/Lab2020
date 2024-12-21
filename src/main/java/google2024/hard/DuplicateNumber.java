package google2024.hard;

public class DuplicateNumber {

    static void findDuplicate3(int[] nums)
    {
        int slow = 0;
        int fast = 1;
        while (true) {

            if(slow != fast && nums[slow] == nums[fast])
                break;
            System.out.println(nums[slow] +" | "+ nums[fast]);
            slow = slow+1;
            fast = fast + 2;

            if(slow >= nums.length)
                slow=0;

            if(fast >= nums.length)
                fast=1;
        }
        System.out.println(nums[slow]);
    }

    public static void main(String[] args) {
        int [] arr = {1,3,2,4,3};
        int n = arr.length;

//        for(int i=0; i<arr.length; i++){
//            for(int j=0; j<arr.length; j++) {
//                if (i != j) {
//                    if (arr[i] == arr[j]) {
//                        System.out.println(arr[i]);
//                        return;
//                    }
//                }
//            }
//        }

        findDuplicate3(arr);
    }

}
