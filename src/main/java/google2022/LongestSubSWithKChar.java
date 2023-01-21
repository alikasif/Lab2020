package google2022;

import java.util.HashMap;
import java.util.Map;

public class LongestSubSWithKChar {
    public static void main(String[] args) {

        int k = 3;
        String s = "aacbb";

        System.out.println(longestSubstring(s, k));

    }

    public static int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;

        char[] chars = new char[26];

        // record the frequency of each character
        for (int i = 0; i < s.length(); i += 1)
            chars[s.charAt(i) - 'a'] += 1;

        boolean flag = true;
        for (int i = 0; i < chars.length; i += 1) {
            if (chars[i] < k && chars[i] > 0)
                flag = false; // set flag as false if any char is less than k. and then find substring.
        }

        // we can also check freq to find if there is any char with atleast k. else break.

        // return the length of string if this string is a valid string
        if (flag == true)
            return s.length();

        int result = 0;
        int start = 0, cur = 0;

        // otherwise we use all the infrequent elements as splits
        while (cur < s.length()) {
            if (chars[s.charAt(cur) - 'a'] < k) {
                result = Math.max(result, longestSubstring(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }

        result = Math.max(result, longestSubstring(s.substring(start), k));
        return result;
    }
}
