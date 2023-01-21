package google2022;

import java.util.Arrays;

public class RotateArray {

    public static int[] rotate(int[] arr, int k) {
        int len = arr.length-1;
        k=k%(len+1);
        arr = rev(arr,0,len-k);
        arr=rev(arr,len-k+1,len);
        arr=rev(arr,0,len);
        return arr;
    }

    public static int[] rev(int[] arr,int low,int hi)
    {
        while(low<hi)
        {
            int temp=arr[low];
            arr[low]=arr[hi];
            arr[hi]=temp;
            hi--;
            low++;
        }
        return arr;
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7};
        int k = 3;
        rotate(arr, k);
        // 0 -> 3 -> 6
        // 1 -> 4 -> 7%7 -> 0
        System.out.println(Arrays.toString(arr));
    }
}
