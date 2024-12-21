package google2024.medium;

public class LongestPalndromicString {
    public static void main(String[] args) {

        String s = "babad";

        int low =0;
        int high = s.length()-1;
        int mid = s.length()/2;
        if (s.length()% 2 == 0) {

        }

        while (low<high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++;
                high--;

            }
        }
    }
}
