package salesforce;

import java.util.*;

public class AllAnagramsInString {
    public static void main(String[] args) {

        String s = "ababacbabab"; // cba  babac
        String p = "aab";

        List<Integer> indices = new ArrayList<>();

        char[] pArray = p.toCharArray();
        Arrays.sort(pArray);

        String tmp = "";

        for(int i=0; i<s.length(); i++) {

            Character c = s.charAt(i);

            if(!s.contains(String.valueOf(c))) {
                tmp = "";
                continue;
            }

            tmp = tmp + String.valueOf(c);


            if(tmp.length() == p.length()) {

                char[] tArray = tmp.toCharArray();
                Arrays.sort(tArray);

                if(Arrays.equals(pArray, tArray)) {
                    System.out.println(tmp);
                    indices.add(i - p.length()+1);
                }
                tmp = tmp.substring(1);
            }
        }
        System.out.println(indices);
    }
}
