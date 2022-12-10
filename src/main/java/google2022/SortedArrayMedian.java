package google2022;

import java.util.Arrays;

public class SortedArrayMedian {

    static void median1 (int[] arr1, int[] arr2){
        int i = 0;
        int j = 0;
        int c = (arr1.length + arr2.length)/2;

        int m1 = 0;
        int m2 = m1;

        int m = (arr1.length + arr2.length)/2;

        while ( c >=0 && i < arr1.length && j < arr2.length ) {
            c--;
            if ( arr1[i] <= arr2[j]) {
                m2 = m1;
                m1 = arr1[i];
                i++;
            }
            else {
                m2 = m1;
                m1= arr2[j];
                j++;
            }
        }

        while ( c - i >= 0 ) {
            m2 = m1;
            m1 = arr1[i];
            i++;
        }

        while ( c - j >= 0 ) {
            m2 = m1;
            m1 = arr2[j];
            j++;
        }

        System.out.println(c + " " + i +" "+j);
        System.out.println(m1 + " " + m2 + " median: " + (m1+m2)/2.0);
    }

    static void median2(int[] arr1, int[] arr2) {
        int m1=0;
        int m2=0;
        while (arr1.length >0 && arr2.length >0) {

            System.out.println(Arrays.toString(arr1) +" "+ Arrays.toString(arr2));
            m1 = arr1.length % 2 == 0 ? (arr1[arr1.length / 2] + arr1[(arr1.length / 2) - 1]) / 2 : arr1[arr1.length / 2];
            m2 = arr2.length % 2 == 0 ? (arr2[arr2.length / 2] + arr2[(arr2.length / 2) - 1]) / 2 : arr2[arr2.length / 2];

            //System.out.println(m1 + " " + m2);

            if (m1 == m2) {
                System.out.println(m1);
                return;
            }

            if (m1 < m2) { // grow arr1 & shrink arr2
                arr1 = Arrays.copyOfRange(arr1, arr1.length / 2, arr1.length);
                arr2 = Arrays.copyOfRange(arr2, 0, (arr2.length / 2)+1);
            }
            else {
                arr1 = Arrays.copyOfRange(arr1, 0, (arr1.length / 2)+1);
                arr2 = Arrays.copyOfRange(arr2, arr2.length / 2, arr2.length);
            }
        }
        System.out.println(m1 + " " + m2);
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        // 1,2 3,4,5,6,7,8,9

        median2(arr1, arr2);

    }
}