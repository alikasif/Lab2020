package leetcode;

public class SearchRotatedArray {

    public static void main(String[] args) {

        int[] arr= {4,5,6,7,0,1,2};
        System.out.println(search(arr, 3, 0, arr.length-1));
    }

    static int search(int[] arr, int tgt, int low, int high) {

        if ( low == high)
            return -1;

        int mid = (low+high)/2;
        System.out.println("mid => "+mid);
        if ( arr[mid] == tgt) {
            System.out.println("found at -> "+ mid);
            return mid;
        }

        if ( mid < arr.length-1 && arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]) {
            if(arr[low] <= tgt && arr[mid-1] >= tgt) {
                search(arr, tgt, low, mid-1);
            }
            else {
                search(arr, tgt, mid+1, high);
            }
        }
        else if (arr[mid] > tgt) {
            search(arr, tgt, low, mid-1);
        }
        else {
            search(arr, tgt, mid+1, high);
        }
        return -1;
    }
}
