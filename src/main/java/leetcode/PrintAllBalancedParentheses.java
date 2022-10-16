package leetcode;

public class PrintAllBalancedParentheses {

    public static void main(String[] args) {
        printAll("", 3, 0, 0);
    }

    static void printAll(String s, int n, int ob, int cb) {
        if( n == ob && n == cb) {
            System.out.println(s);
            return;
        }
        if( ob < n) {
            printAll(s+"{",n, ob+1, cb);
        }

        if( ob  > cb) {
            printAll(s+"}", n, ob, cb+1);
        }

    }
}
