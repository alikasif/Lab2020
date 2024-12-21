package google2024.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        int[] arr = {2,3,6,7};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        findSum(arr, new ArrayList<>(), 0, 7);
    }

    public static void findSum(int[] arr, List<Integer> list, int sum, int target) {

        if (sum == target){
            System.out.println(list);
            return;
        }

        for(int x : arr) {
            if(sum + x <= target) {
                list.add(x);
                findSum(arr, list,sum+x, target);
                list.remove(list.size()-1);
            }
        }
    }
}
