package google2022;

import java.util.HashMap;
import java.util.Map;

public class PartitionLabels {
    public static void main(String[] args) {
        String s = "bacbdegdh";
        Map<Character, Integer> map = new HashMap<>();

        for(int i=0; i<s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        int ei = -1;
        for(int i=0; i<s.length(); i++) {
            ei = Math.max(ei, map.get(s.charAt(i)));
            if(ei == i) {
                System.out.println("partition at "+ i);
                ei =-1;
            }
        }
    }
}
