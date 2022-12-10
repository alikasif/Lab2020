package google2022;

public class SmallestPositiveMissingNumber {
    public static void main(String[] args) {

        int[] arr = {3,4,-1,1};
        // 0,1,2,3;
        // 1,2,3,4;

        for(int i=0; i< arr.length; i++) {
            if (arr[i] == i+1)
                continue;
            else {
                int x = arr[i];
                arr[i] = arr[x];
            }
        }
    }
}
