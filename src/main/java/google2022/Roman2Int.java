package google2022;

import java.util.HashMap;
import java.util.Map;

public class Roman2Int {

    public static void main(String[] args) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int n = 0;

        String roman = "VX";
        Character p = null;
        for(Character c : roman.toCharArray()) {

            if(p == null) {
                p = c;
            }
            else {

                if( (c == 'V' && p == 'I') || (c == 'X' && p == 'I') ) {
                    n = n + map.get(c)- map.get(p);
                    p = null;
                }
                else if ( (c == 'L' && p == 'X') || (c == 'C' && p == 'X') ) {
                    n = n + map.get(c)- map.get(p);
                    p = null;
                }
                else if ( (c == 'D' && p == 'C') || (c == 'M' && p == 'C') ) {
                    n = n + map.get(c)- map.get(p);
                    p = null;
                }
                else {
                    n = n + map.get(p);
                    p = c;
                }
            }
        }
        if(p != null)
            n = n+ map.get(p);
        System.out.println(n);
    }
}
