package google2022;

public class PeakElement {
    public static void main(String[] args) {

        int[] arr= {1,2,3,1};

        if ( arr[0]> arr[1])
            System.out.println(arr[0]);
        if(arr[arr.length-1] > arr[arr.length-2])
            System.out.println(arr[arr.length-1]);

        int l =0;
        int r= arr.length-1;
        int m = 0;

        while ( l < r ) {
            m = (l+r)/2;
            System.out.println("l " +l +" r "+r +" m "+m);
            if (arr[m] > arr[m-1] && arr[m] > arr[m+1])
                break;
            else if(arr[m] > arr[m+1])
                r = m;
            else
                l = m;
        }
        System.out.println(m);
    }
}
