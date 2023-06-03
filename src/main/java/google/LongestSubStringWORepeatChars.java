package google;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWORepeatChars {

    public static void main(String[] args) {

        String str = "GEEKSFORGEEKS";

        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int k = 0;

        while ( i <str.length() ) {

            if(!map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), i);
            }
            else {
                System.out.println(str.substring(k, i));
                k = map.get(str.charAt(i));
                for(int j = 0; j<k+1; j++) {
                    System.out.println("removing "+ str.charAt(j));
                    map.remove(str.charAt(j));
                }
                map.put(str.charAt(i), i);
            }
            i++;
        }
        System.out.println(str.substring(k, str.length()));
    }
}
