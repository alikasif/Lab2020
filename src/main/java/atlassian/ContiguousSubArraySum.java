package atlassian;

public class ContiguousSubArraySum {
    public static void main(String[] args) {

        // 1 2 3 4 5 6
        //3 4 5 6 1 2

        int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};

        int ms = arr[0];
        int cs= arr[0];

        for(int i=1; i<arr.length; i++){

            cs = Math.max(cs + arr[i], arr[i]);
            ms = Math.max(cs, ms);
        }
        System.out.println(ms);
    }
}
