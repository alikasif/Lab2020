package google;
// Java program to count number of times
// S appears as a subsequence in T
import java.io.*;
import java.util.Arrays;

class GFG {
    static int findSubsequenceCount(String S, String T)
    {
        int m = T.length();
        int n = S.length();

        // T can't appear as a subsequence in S
        if (m > n)
            return 0;

        // mat[i][j] stores the count of
        // occurrences of T(1..i) in S(1..j).
        int mat[][] = new int[m + 1][n + 1];

        // Initializing first column with
        // all 0s. An emptystring can't have
        // another string as suhsequence
        for (int i = 1; i <= m; i++)
            mat[i][0] = 0;

        // Initializing first row with all 1s.
        // An empty string is subsequence of all.
        for (int j = 0; j <= n; j++)
            mat[0][j] = 1;
        printMtrix(mat,m, n);
        // Fill mat[][] in bottom up manner

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (T.charAt(i - 1) != S.charAt(j - 1))
                    mat[i][j] = mat[i][j - 1];
                else
                    mat[i][j] = mat[i][j - 1] + mat[i - 1][j - 1];
            }
        }
        printMtrix(mat, m, n);

        return mat[m][n];
    }

    static void printMtrix(int[][] mat, int r, int c) {
        for(int i=0;i<r;i++){
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    // Driver code to check above method
    public static void main(String[] args)
    {
        String T = "ge";
        String S = "geeksforgeeks";
        System.out.println(findSubsequenceCount(S, T));
    }
}
// This code is contributed by vt_m
