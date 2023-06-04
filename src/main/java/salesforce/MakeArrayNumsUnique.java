package salesforce;

import java.util.HashMap;
import java.util.Map;

public class MakeArrayNumsUnique {
    public static void main(String[] args) {

        int[] arr = {3,2,1,2,1,7};
        int c=0;
        Map<Integer, Integer> freqmap = new HashMap<>();
        for(int x : arr) {
            if(freqmap.get(x) == null) {
                freqmap.put(x,1);
            }
            else {
                while (freqmap.containsKey(x)) {
                    x++;
                    c++;
                }
                freqmap.put(x,1);
            }
        }
        System.out.println(freqmap);
        System.out.println(c);
    }
}
