package google2022;

public class AddBinary {

    public static void main(String[] args) {

        String a = "1111";
        String b = "1111";

        while(b.length() < a.length()) {
            b = "0"+b;
        }
        while(a.length() < b.length()) {
            a = "0"+a;
        }
        System.out.println(b +" "+a);

        String result = "";
        int i = a.length()-1;
        int r = 0;
        while (i>=0) {

            int s = Integer.valueOf(a.substring(i,i+1)) + Integer.valueOf(b.substring(i,i+1));
            s = s+r;

            if(s==3) {
                result = "1" + result;
                r=1;
            }
            else if(s==2) {
                result = "0" + result;
                r=1;
            }
            else {
                result = s + result;
                r=0;
            }
            i--;
        }
        if(r == 1)
            result = r + result;

        System.out.println(result);
    }

}
