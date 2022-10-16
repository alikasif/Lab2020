package google2022;

public class MaxWaterContainer {

    public static void main(String[] args) {

        int[] heights = {1,8,6,2,5,4,8,3,7};
        maxHeight1(heights);
        maxHeight2(heights);
    }

    static void maxHeight1(int[] arr) {
        int n= arr.length;
        int ans= Integer.MIN_VALUE;

        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                int a = Math.min(arr[i], arr[j])*(j-i);
                ans = Math.max(ans, a);
            }
        }
        System.out.println(ans);
    }

    static void maxHeight2(int[] arr) {
        int n= arr.length;
        int ans= Integer.MIN_VALUE;
        int s= 0;
        int e = n-1;

        while (s < e) {
            int a = (e-s) * Math.min(arr[s], arr[e]);
            ans = Math.max(ans, a);

            if (arr[s] < arr[e])
                s++;
            else
                e--;
        }
        System.out.println(ans);
    }
}
