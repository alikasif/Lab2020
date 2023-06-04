package salesforce;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharReplacement {
    public static void main(String[] args) {
        String s = "AABABBA";
        characterReplacement(s, 1);
    }

   static public int characterReplacement(String s, int k) {

        Map<Character,Integer> map=new HashMap<>();

        int start=0;
        int maxCountRepeatingCh=0;
        int maxLength=0;

        for(int i=0;i<s.length();i++){

            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
            maxCountRepeatingCh = Math.max(maxCountRepeatingCh,map.get(s.charAt(i)));

            System.out.println(i +" "+ start +" "+maxCountRepeatingCh);

            while( (i-start+1) - maxCountRepeatingCh > k )  {          //  what does this do??
                map.put(s.charAt(start), map.get(s.charAt(start))-1);
                start++;
            }

            maxLength = Math.max(maxLength,i-start+1);

        }
        System.out.println(maxLength);
        return maxLength;
    }
}
