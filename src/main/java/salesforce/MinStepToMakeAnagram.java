package salesforce;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinStepToMakeAnagram {

    public static void main(String[] args) {

        String s = "aabcc";
        String t = "abbcd";

        Map<Character, Integer> smap = new HashMap<>();
        Map<Character, Integer> tmap = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            smap.put(s.charAt(i), smap.getOrDefault(s.charAt(i), 0)+1);
        }

        for(int i=0; i<t.length(); i++){
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0)+1);
        }

        int c=0;
        for(Character cc : tmap.keySet()) {

            if(smap.get(cc) == null) {
                c+=tmap.get(cc);
                continue;
            }
            else {
                int c1 = tmap.get(cc);
                int c2 = smap.get(cc);
                System.out.println(cc +" "+c1+" "+c2);
                if(c1 > 1 ) {
                    c = c + Math.abs(c2-c1);
                }
            }
        }
        System.out.println(c);
    }
}
