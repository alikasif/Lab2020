package fb;

public class Kadane {

    public static void main(String[] args) {

        int gmax =0;
        int cmax =0;

        int arr[] = {1,2,3,-2,-7, 3};

        int i = 0;
        while ( i < arr.length ) {
            cmax = Math.max(0, cmax+arr[i]);
            gmax = Math.max( gmax, cmax);
            i++;
        }
        System.out.println(gmax);
    }
}
