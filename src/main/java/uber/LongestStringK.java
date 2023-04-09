package uber;

public class LongestStringK {
    public static void main(String[] args) {

        String s = "AABCDBA";
        int k = 1;
        int k1=k;
        int j = 0;
        int x =0;
        int maxlen =0;

        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == s.charAt(j)) {
                continue;
            }
            else {
                x=i;
                if( k > 0) {
                    k--;
                }
                else {
                    int len = i-j;
                    maxlen = Math.max(maxlen, len);
                    k = k1;
                    j = x;
                    i = x;
                }
            }
        }
        System.out.println(maxlen);
    }
}
