package atlassian;

import java.util.HashMap;
import java.util.Map;

public class MakeArrayElementDistinct {
    public static void main(String[] args) {

        int [] arr = {7,1,1,1};
        Map<Integer, Integer> map = new HashMap<>();
        int c=0;

        for(int k : arr) {
            Integer integer = map.get(k);
            if(integer == null)
                map.put(k, 1);
            else {
                while (map.containsKey(k)) {
                    k++;
                    c++;
                }
                map.put(k, 1);
            }
        }

        System.out.println(map);
        System.out.println(c);
    }
}
