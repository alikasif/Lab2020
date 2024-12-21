package google2024.medium;

import java.util.Arrays;

public class MinInSortedArray {

    static int findMin(int[] a) {

        int l=0,r=a.length-1;

        int min=Integer.MAX_VALUE;

        while(l<r){
            int mid=(l+r)/2;
            if(a[mid]<a[r]){
                r=mid;
            }else{
                l=mid+1;
            }
        }
        return a[l];
    }

    public static void main(String[] args) {


        // 1 2 3 4 5
        int [] arr = {3,4,5,1,2};
        System.out.println(Arrays.toString(arr));

        System.out.println(findMin(arr));

        int l = 0;
        int r = arr.length - 1;
        int pivot = -1;

        while(l < r && pivot == -1) {

            int m = (l+r)/2;

            if (arr[l] > arr[l+1]) {
                pivot = l + 1;
                break;
            }

            if (arr[r] < arr[r-1]) {
                pivot = r -1;
                break;
            }

            if ( (arr[m-1] < arr[m] && arr[m] > arr[m+1]) || (arr[m-1] > arr[m] && arr[m] < arr[m+1]) )
            {
                pivot = m;
                break;
            }

            l++;
            r--;
        }
        System.out.println(pivot);


    }
}
