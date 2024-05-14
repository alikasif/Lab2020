package dp;

public class MinStepTo1 {
    public static void main(String[] args) {
        int n = 5;
        // n = n-1
        // n = n/2 if n%2==0
        // n = n/3 if n%3 ==0

        int[] dp = new int[n+1];
        // each array element store the result for index
        // dp[1] = 0 bcoz no step is required to make it 1
         // dp[2] => 1 + dp[1]
        System.out.println(getMinSteps(3));
    }

    static int getMinSteps ( int n )
    {
        int[] dp = new int[n+1];
        int  i;

        dp[1] = 0; // trivial case
        for( i = 2 ; i<= n; i ++ )
        {
            dp[i] = 1 + dp[i-1];

            if(i%2==0)
                dp[i] = Math.min( dp[i] , 1+ dp[i/2] );

            if(i%3==0)
                dp[i] = Math.min( dp[i] , 1+ dp[i/3] );
        }
        return dp[n];
    }

    static int longestIncSubSeq(int[] n) {
        //10,9,2,5,3,7,101,18  ==> 2,3,7,101 or 2,5,7,101

        int[] dp = new int[n.length+1];
        dp[1] = 1;

        if(n.length == 1)
            return 1;

        for(int i = 2; i<n.length+1; i++) {

        }
        return 1;
    }
}