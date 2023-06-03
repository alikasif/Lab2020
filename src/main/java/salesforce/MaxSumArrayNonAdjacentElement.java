package salesforce;

public class MaxSumArrayNonAdjacentElement {
    public static void main(String[] args) {

        int[] arr = {5, 5, 10, 100, 10, 5};

        int s1=0;
        int s2=0;

        s1 = arr[0];
        s2 = arr[1];

        for(int i= 2; i<arr.length; i++) {
            if(s1 + arr[i] > s2) {
                s1 = s1+arr[i];
            }
            else {

            }
        }
    }
}
