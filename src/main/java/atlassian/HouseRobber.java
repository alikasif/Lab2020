package atlassian;

public class HouseRobber {

    public static void main(String[] args) {

        int[] arr = {1,5,1,2,6};
        // 1 5 1 2 6
        // 5 1 2 6
        int ms = arr[0];
        int cs = arr[0];
        int li = 0;

        for(int i = 1; i< arr.length; i++) {

            if(i == li+1) {
                if(arr[i] > ms) {
                    ms = arr[i];
                    li = i;
                }
            }
            else {
                cs = cs + arr[i];
                li =i;
            }
        }
    }
}
