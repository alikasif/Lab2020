package salesforce;

public class StringFromRepetition {
    public static void main(String[] args) {

        String s = "aaa";

        int i = 0;
        int j = 1;
        int period =-1;

        while (j < s.length()) {

            Character currChar = s.charAt(j);

            if(currChar == s.charAt(i)) {
                period = j-i;
                i++;
            }
            else if (currChar == s.charAt(0)) {
                period = j;
                i=1;
                System.out.println("reset i to 1");
            }
            else {
                i=0;
                period=-1;
            }
            j++;
        }

        System.out.println(period);

    }
}
