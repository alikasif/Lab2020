package google2022;

import java.util.HashMap;
import java.util.Map;

public class LongestSubSWithKChar {
    public static void main(String[] args) {

        int k = 2;
        String s = "aaabb";

        Map<String, Integer> map = new HashMap<>();

        int maxuc =2;
        int uc = 1;
        int c=0;

        while (uc <= maxuc) {
            c=0;
            int j = -1;

            for(int i =0; i<s.length(); i++) {
                if(j == -1)
                    j=i;
                String t = String.valueOf(s.charAt(i));
                if (map.containsKey(t)) {
                    map.put(t, map.get(t)+1);
                }
                else {
                    map.put(t,1);
                    c++;
                }

                if (c == uc && map.get(t) >= k){
                    System.out.println(i-j+1);
                    System.out.println(uc);
                }

                if(c > uc) {
                    break;
                }
            }
            uc++;
        }


    }
}
