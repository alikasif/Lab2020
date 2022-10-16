package google2022;

public class ArrayProduct {
    public static void main(String[] args) {

        int[] arr=  {10,3,5,6,2};

        int [] left = {10, 30, 150, 900, 1800};
        int [] right = {1800, 180, 60,12,2};

        for (int i=0; i<arr.length; i++) {
            int li = i-1;
            int ri = i+1;
            int t = 1;

            if (li >=0) {
                t =t*left[li];
            }
            if (ri <arr.length) {
                t = t* right[ri];
            }
            System.out.println(i +":" + arr[i] + ":"+t);
        }
    }
}
