package google2022;

public class LongestPalindromicSubString {

    public static void main(String[] args) {
        String input = "qbafjabz";
        // bab aba
        longestPalindrome2(input);
    }
    static void longestPalindrome2(String s) {

        for(int i=0; i< s.length(); i++) {
            checkPalindrome(s, i, i);
            checkPalindrome(s, i, i+1);
        }
    }
    private static void checkPalindrome(String s, int i, int j) {
        while ( i>=0 && j<s.length() && s.charAt(i) == s.charAt(j) ) {
            i--;
            j++;
        }
        if( i+1 < j )
            System.out.println("maxlen: " + i +" "+j + " "+ s.substring(i+1, j));
    }

    static String longestPalindrome(String s) {

        if (s.equals("")) return "";

        if (s.length() == 1) return s;

        int min_start = 0, max_len = 1;

        for (int i = 0; i < s.length();) {

            if (s.length() - i <= max_len / 2)
                break;
            int j = i, k = i;

            while (k < s.length()-1 && s.charAt(k+1) == s.charAt(k))
                ++k; // Skip duplicate characters.
            i = k+1;

            while (k < s.length()-1 && j > 0 && s.charAt(k + 1) == s.charAt(j - 1)) {
                ++k; --j;
            } // Expand.

            int new_len = k - j + 1;
            if (new_len > max_len) {
                min_start = j; max_len = new_len;
            }
        }
        return s.substring(min_start, max_len);
    }
}
