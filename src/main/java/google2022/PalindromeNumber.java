package google2022;

public class PalindromeNumber {

    public static void main(String[] args) {

        int x = 10;
        int c = x;
        int p = 0;

        while (x !=0) {
            int r = x % 10;
            p = p * 10 + r;
            x = x / 10;
        }
        if( p == c)
            System.out.println("palindrome number");

    }
}
