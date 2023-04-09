package uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSun2 {
    public static void main(String[] args) {

        int[] arr = {10,1,2,7,6,1,5};
        int tgt = 8;
        combinationSum2(arr, tgt);
    }

    public static List<List<Integer>> combinationSum2(int[] arr, int target) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        List<List<Integer>>ans=new ArrayList<>();
        solve(0,target,arr,ans,new ArrayList<>());
        System.out.println(ans);
        return ans;
    }

    public static void solve(int index, int target,int[]arr, List<List<Integer>>ans, List<Integer>temp) {
        System.out.println(temp);
        if (target == 0) {
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < arr.length; i++) {

            if (i != index && arr[i] == arr[i - 1])
                continue;

            if (arr[i] > target)
                break; // as the array is sorted

            temp.add(arr[i]);
            solve(i + 1, target - arr[i], arr, ans, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
