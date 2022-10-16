package leetcode;

public class HIndex {

    public static void main(String[] args) {

        //int[] arr = {0, 1, 3, 5, 6};
        int[] arr = {2,2,3,3,5};
        find(arr);
    }

    static void find(int[] arr) {

        int lo = 0;
        int hi = arr.length-1;
        int i = 0;

        while ( lo <= hi ) {

            int m = (lo+hi)/2;
            System.out.println("M: "+m +" hIndex: "+i);

            if ( arr[m] <= (m+1) ) {
                lo = m + 1;
                i = m + 1;
            }
            else {
                hi = m - 1;
            }
        }
        System.out.println(i);
    }
}