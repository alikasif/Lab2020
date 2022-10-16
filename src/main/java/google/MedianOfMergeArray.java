package google;

import java.util.PriorityQueue;

public class MedianOfMergeArray {

    public static void main(String[] args) {

        int[] arr1 = {1, 4, 5};
        int[] arr2 = {2};


        int f = 0;
        int s = 0;
        int v = (arr1.length + arr2.length);

        if( v%2 == 0 ) {
            f = v/2;
            s = f+1;
        }
        else {
            f = (int) Math.ceil(v / 2.0);
        }

        int i = 0;
        int j =0;
        int popped = -1;
        int k = 0;

        while (i < arr1.length && j < arr2.length) {

            k++;

            if(arr1[i] < arr2[j]) {
                popped = arr1[i];
                i++;
            }
            else {
                popped = arr2[j];
                j++;
            }

            if( k == f) {
                if( s == 0 ) {
                    System.out.println(popped);
                }
                else {
                     int tmp = arr1[i] < arr2[j]? arr1[i] : arr2[j];
                    System.out.println((popped+tmp)/2.0);
                }
                break;
            }
        }
    }

    static void algo2(int[] arr1, int[] arr2) {
    }
}
