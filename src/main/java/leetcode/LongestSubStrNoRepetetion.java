package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStrNoRepetetion {

    public static void main(String[] args) {

        String str = "abcabcbb";
        str = "geeksforgeeks";
        str = "pwwkew";

        int length = 0;
        int ptr = 0;
        Map<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < str.length(); i++) {

            if ( map.containsKey(str.charAt(i)) && map.get(str.charAt(i)) >0 ) {
                ptr = Math.max(ptr, map.get(str.charAt(i))+1);
            }

            map.put(str.charAt(i), i);
            length = Math.max(length, (i-ptr)+1);

            System.out.println(map +" "+length +" "+ptr);
        }

        System.out.println(length);
    }
}
