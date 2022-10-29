package google2022;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubString {

    public static void main(String[] args) {

        String S = "ADOBECODEBANC";
        String T = "ABC";

        // A -> 0, 10
        // B -> 3, 9
        // C -> 5, 12

        // 0 3 5 9 10 12
        // ABCBAC

        Map<String, Integer> need = new HashMap<>();
        Map<String, Integer> have = new HashMap<>();

        int needSize = T.length();
        int haveSize =0;

        for(int i=0; i<T.length(); i++) {
            String s = String.valueOf(T.charAt(i));
            need.put(s, need.get(s)== null?1: need.get(s)+1);
        }
        System.out.println(need);

        int l=-1;
        int r=-1;
        int minl = Integer.MAX_VALUE;

        for(int i=0; i<S.length(); i++) {
            r=i;
            String s = String.valueOf(S.charAt(i));
            if (need.containsKey(s)) {
                
                if(have.get(s) == null || have.get(s) ==0){
                    haveSize++;
                }

                if (l == -1){
                    l = i;
                }

                have.put(s, have.get(s)== null?1: have.get(s)+1);
            }
            if(haveSize == needSize) {

                System.out.println("l = "+ l +" R = "+r);
                minl = Math.min(minl, (r-l)+1);

                while (l < i) {
                    String s1 = String.valueOf(S.charAt(l));
                    if(have.containsKey(s1)) {
                        if(haveSize < needSize) {
                         break;
                        }
                        else {
                            have.put(s1, have.get(s1) == 0 ? 0 : have.get(s1) - 1);
                            if (have.get(s1) == 0) {
                                haveSize--;
                            }
                        }
                    }
                    l++;
                }
            }
        }
        System.out.println(" final  l = "+ l +" R = "+r);
        System.out.println(minl);
    }
}
