package atlassian;

public class UniquePath {

    static int count=0;
    public static void main(String[] args) {

        int m = 0, n = 5;

        int r=0;
        int c=0;
        findPath(r,c,m,n);
        System.out.println(count);
    }
    static void findPath(int r, int c, int m, int n) {

        System.out.println(r+","+c);

        if(r == m && c == n) {
            count++;
            return;
        }

        if( r+1 <= m) {
            findPath(r+1, c, m, n);
        }

        if(c +1 <= n) {
            findPath(r, c+1, m, n);
        }
    }
}
