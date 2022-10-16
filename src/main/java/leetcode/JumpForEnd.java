package leetcode;

public class JumpForEnd {

    public static void main(String[] args) {

        int[] arr = {3,3,1,0,2,0,1};
                    // 0,1,2,3,4,5,6
                    // 3,4,3,3,6,5,7

        int m = 0;
        for(int i = 0 ;i < arr.length && m<arr.length-1 ; i++) {
            m = Math.max(m, i+arr[i]);
        }
        System.out.println(m);
    }
}
