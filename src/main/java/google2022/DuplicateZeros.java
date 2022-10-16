package google2022;

import java.util.Arrays;

public class DuplicateZeros {

    public static void main(String[] args) {

        //int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};

        // 1 0 0 2 3 0 0 4
        // int[] arr = {1, 2, 3};

        //int[] arr = {8, 5, 0, 9, 0, 3, 4, 7};
        int[] arr = {8, 4, 5, 0, 0, 0, 0, 7};
        //duplicateZeros(arr);
        dup2(arr);
        // 8 5 0 0 9 0 0 3
        // 1 0 0 2 3 0 0 4
        // 1 0 2 3  0 4 5 4
        // 1 0 2 3 0 0 0 4
        // 1 0 2 3 3 0 0 4
        // 1 0 2 2 3 0 0 4
        // 1 0 0 2 3 0 0 4
        System.out.println(Arrays.toString(arr));

    }
    static void duplicateZeros(int[] a) {
        int i = 0, sh = 0;

        for (i = 0; sh + i < a.length; ++i)
            sh += a[i] == 0 ? 1 : 0;

        System.out.println(i +" "+sh);

        for (i = i - 1; sh > 0; --i) {

            if (i + sh < a.length)
                a[i + sh] = a[i];

            if (a[i] == 0)
                a[i + --sh] = a[i];
        }
    }

    static void dup2(int[] arr) {

        int countZero = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                countZero++;
        }
        if(countZero == 0 || countZero == arr.length-1)
            return;

        int j = arr.length - 1;
        int i = arr.length - countZero-1;
        System.out.println(i +" "+j);

        for(int k =i+1; k<arr.length; k++) {
            if(arr[k] == 0)
                i++;
        }

        System.out.println(i +" "+j);

        while (i >= 0) {
            System.out.println(Arrays.toString(arr) + " "+i +" " +j);

            if (arr[i] != 0) {
                arr[j] = arr[i];
                j--;
                i--;
            }
            else {
                arr[j] = 0;
                j--;
                arr[j] =0;
                j--;
                i--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}