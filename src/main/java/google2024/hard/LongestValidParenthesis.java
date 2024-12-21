package google2024.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LongestValidParenthesis {
    public static void main(String[] args) {

        String s = "(()";

        Stack<String> stack = new Stack<>();

        List<Integer> indices = new ArrayList<>();

        for(int i=0; i<s.length(); i++) {
            String t = String.valueOf(s.charAt(i));

            if (t.equals("("))
                stack.push(t);

            if( t.equals(")") && stack.isEmpty() ) {
                continue;
            }

            if(t.equals(")") &&  stack.peek().equals("(") ) {
                stack.pop();
                indices.add(i);
            }
        }

        System.out.println(indices);
    }
}
