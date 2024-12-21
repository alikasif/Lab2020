package google2024.medium;

import java.util.Arrays;

public class ReverseWordsINString {

    static void reverse(char[] charArray, int i, int j) {

        while (i < j){
            char c = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] =c;
            i++;
            j--;
        }
    }

    static void reverse(String s) {
        char[] charArray = s.toCharArray();
        reverse(charArray, 0, charArray.length-1);
        System.out.println(Arrays.toString(charArray));

        int j=0;
        int i=0;
        while (j<charArray.length) {
            if(charArray[j] != ' ') {
                j++;
            }
            else {
                reverse(charArray, i, j-1);
                i = j;
                j++;
                i++;
            }
        }
        reverse(charArray, i, j-1);
        System.out.println(Arrays.toString(charArray));
    }

    public static void main(String[] args) {
        String s = "the sky is blue";

        // blue is sky the
        // yks eht
        // sky the

        String[] str = new String[4];
        int j=0;
        String tmp = "";
        String ctmp = "";
        for(int i=0; i<s.length(); i++) {
            String t = String.valueOf(s.charAt(i));
            if (t.equals(" ")) {
                str[j] = tmp;
                tmp  = "";
                j++;
            }
            else {
                tmp = tmp + t;
            }
        }
        str[j] = tmp;
        System.out.println(Arrays.toString(str));

        reverse(s);

    }

}
