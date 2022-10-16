package leetcode;

public class MaxCharBYCTrlACV {

    public static void main(String[] args) {

        System.out.println(optimal(7));
    }

    static int optimal(int n) {

        if (n <=6)
            return n;

        int max = 0;

        for(int b = n-3; b>=1; b--) {
            int curr = (n - b - 1);
            System.out.println(b + " " +curr);
            curr = curr * optimal(b);
            if(curr > max)
                max = curr;
        }

        return max;
    }
}
