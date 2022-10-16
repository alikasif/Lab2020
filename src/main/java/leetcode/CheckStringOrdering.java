package leetcode;

public class CheckStringOrdering {

    public static void main(String[] args) {

        String s = "engineers rock";

        //String p1 = "er"; // true
        String p1 = "egr"; // false
        String p3 = "gsr"; // false

        // get index of each char
        // e -> 0 5 6
        // r  -> 7 9
        // g-> 2
        // s -> 8

        for(int i =0; i< p1.length()-1; i++) {

            int x = s.lastIndexOf(p1.charAt(i));
            int y = s.indexOf(p1.charAt(i+1));

            if ( y < x)
                return;
        }
        System.out.println("true");

    }
}
