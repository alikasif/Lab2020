package google2024.hard;

public class CreateMaxNumber {
    public static void main(String[] args) {

        int[] arr1 = {-2,5,-1};
        int l = -2;
        int u = 2;

        int i = countRangeSum(arr1, l, u);
        System.out.println(i);

    }

    static public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j)
                if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)
                    ans++;
        return ans;
    }
}
