package google2024.hard;

import java.util.HashSet;
import java.util.Set;

public class SubStringConcat {
    public static void main(String[] args) {

        String str = "barfoofoobarthefoobarman";
        Set<String> words = new HashSet<>();
        words.add("foo");
        words.add("bar");
        words.add("the");

       int l = 3;
       String finalWord = "";
       String tmp = "";

       for(int i=0; i<str.length(); i++) {
            tmp = tmp + str.charAt(i);
            if(tmp.length() == l) {
                if(words.contains(tmp)) {
                    if(!finalWord.contains(tmp)) {
                        finalWord = finalWord + tmp;
                    }
                    else {
                        System.out.println(finalWord);
                        finalWord = finalWord.substring(finalWord.indexOf(tmp)+tmp.length(), finalWord.length());
                        finalWord = finalWord+tmp;
                        System.out.println(finalWord);
                    }
                }
                else {
                    System.out.println(finalWord);
                    finalWord="";
                }
                tmp = "";
            }
       }
    }
}
