package google2024.medium;

public class MaxPointsFromCard {

    public static void main(String[] args) {

        //int[] arr = {1,23,3,4,5,6,1};

        int[] arr = {5,4,-1,4,2,-2,1,6};
        int k = 4;

        int i = -1;
        int j = arr.length;
        int sum = 0;

        System.out.println(sum);

        while (++i < k)
            sum = sum + arr[i];

        System.out.println(sum);

        int maxsum = sum;
        i--;
        j--;
        System.out.println(i + " | " + j);

        while (j>0) {

            sum = sum - arr[i];
            sum = sum + arr[j];

            i--;
            System.out.println(" indexes :: "+i + " | " + j);
            j--;

            maxsum = Math.max(maxsum, sum);
            System.out.println(" maxSum :: "+sum + " | " + maxsum);

            if( i <0)
                i = arr.length-1;

        }
    }
}
