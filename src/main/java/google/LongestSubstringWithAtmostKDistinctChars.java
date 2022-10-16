package google;

import java.util.*;

public class LongestSubstringWithAtmostKDistinctChars {

    public static void main(String[] args) {

        String str = "cbbebi";
        int l = 3;
        Map<Character, Integer> map = new HashMap<>();

        int i = 0;
        int k = 0;

        while (i < str.length()) {

            System.out.println(map);
            map.put(str.charAt(i), i);

            if( map.size() <= l ) {
                System.out.println(str.substring(k, i+1));
            }

            while (map.size() > l) {
                int j = map.get(str.charAt(k));
                map.remove(str.charAt(k));
                k = j+1;
            }
            i++;
        }
        System.out.println(str.substring(k, i));
    }
}
