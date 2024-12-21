package google2024.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStrWithoutRepeat {
    public static void main(String[] args) {

        String s = "pwwkew";
        int min = 0;

        int i = 1;
        while (i < s.length()) {
            String subs = s.substring(min, i);
            String find = String.valueOf(s.charAt(i));

            System.out.println(subs +" | "+ find );
            if(subs.contains(find)) {
                System.out.println("got "+ find + " at "+ s.indexOf(find));
                min = subs.indexOf(find)+min+1;
            }
            i++;
        }
        System.out.println(s.substring(min, i));
        System.out.println("pass2");
        pass2(s);
    }

    static void pass2(String s) {

        int start = 0;
        Map<String, Integer> map = new HashMap<>();
        map.put(String.valueOf(s.charAt(start)), start);

        for(int i=1; i<s.length(); i++) {

            String key = String.valueOf(s.charAt(i));
            if(map.containsKey(key)) {
                start = map.get(key)+1;
            }
            map.put(key, i);
            System.out.println(s.substring(start, i+1));
        }
    }
}
