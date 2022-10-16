package leetcode;

public class FirstLastPos {

    public static void main(String[] args) {

        int[] arr = {5,7,7,8,8,10};
        findPos(arr, 0, arr.length-1, 10);
    }

    static void findPos(int[] arr, int l, int h, int tgt) {

        if (l > h)
            return;

        int mid = (l+h)/2;

        System.out.println("low: "+l+" high: "+h+" mid: "+mid);

        if (arr[mid] == tgt) {

            if ( mid-1 >= 0 && arr[mid-1] != tgt) {
                System.out.println("low " + mid);
            }
            else {
                findPos(arr, l, mid-1, tgt);
            }

            if (mid+1 < arr.length && arr[mid+1] != tgt) {
                System.out.println("high "+ mid);
            }
            else {
                findPos(arr, mid+1, h, tgt);
            }
        }
        else {
            if(arr[mid] < tgt) {
                findPos(arr, mid+1, h, tgt);
            }
            else {
                findPos(arr, l, mid-1, tgt);
            }
        }
    }
}
