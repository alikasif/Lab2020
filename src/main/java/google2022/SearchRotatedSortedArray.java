package google2022;

public class SearchRotatedSortedArray {
    public static void main(String[] args) {

        int[] arr = {7,0,0,2,3,4,5,6};
        int x = 0;

        int l=0;
        int r = arr.length-1;

        while (l<r) {
            int m = (l + r) / 2;
            System.out.println(l +" "+m +" "+r);
            if (x == arr[m]) {
                System.out.println(m);
                break;
            }

            if(arr[m] < arr[l] && arr[m]> x) {
                r = m-1;
            }
            else l = m+1;
        }

    }
}
