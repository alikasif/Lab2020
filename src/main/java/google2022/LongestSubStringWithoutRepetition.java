package google2022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStringWithoutRepetition {

    public static void main(String[] args) {

        String input = "pwiewklew";
        Map<Character, Integer> map = new HashMap<>();
        int last = 0;
        int maxl = 0;

        for(int i=0; i<input.length(); i++) {

            Character c = Character.valueOf(input.charAt(i));

            if (map.containsKey(c)) {
                int tt = map.get(c);
                for (int j = last; j <= tt; j++) {
                    Character c1 = Character.valueOf(input.charAt(j));
                    map.remove(c1);
                }
                last = tt+1;
            }

            map.put(c, i);
            maxl = Math.max(maxl, map.size());
        }
        System.out.println(maxl);
    }
}
