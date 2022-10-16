package fb;

import java.util.Arrays;

public class ArrangeArrayAlternative {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int arr2[] = new int[arr.length];
        // 3 1 2 4 5 6 7 8
        // 8 1
        // 2 4 5 6 7 3
        // 5 2 4 6 7 3
        // 7 2
        // 4 6 5 3
        // 5 4 6 3

        int i = 0;
        int j = arr.length-1;
        int t = 0;
        while ( i < j ) {
            arr2[t] = arr[i];
            t++;
            arr2[t] = arr[j];
            t++;
            i++;
            j--;
        }

        System.out.println(Arrays.toString(arr2));
    }
}
