package google2022;

public class ReverseInteger {

    public static void main(String[] args) {

        int x = 123;
        int a = 10;
        int r = 0;

        while (x > 0) {
            int b = x%a;
            x = x/a;
            r = (r * 10) + b;
            System.out.println(a +" "+b +" "+r +" "+x);
        }
    }
}
