package google2022;

import java.util.Stack;

public class LongestValidParentheses {
    public static void main(String[] args) throws Exception {

        String s = ")(())()()()()()";

        Stack<Character> stack = new Stack<>();
        int ci = 0;
        int mc = 0;

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                if(stack.isEmpty()) {
                    stack.push(c);
                    mc = Math.max(mc, ci);
                }
                else {
                    if (stack.peek() == '(') {
                        stack.push(c);
                        mc = Math.max(mc, ci);
                    }
                    else {
                        throw new Exception("invalid char found " + stack.peek());
                    }
                }
            }
            else {
                if (stack.isEmpty()) {
                    ci=0;
                    continue;
                }
                else {
                    if (stack.peek() == '(') {
                        stack.pop();
                        ci++;
                        mc = Math.max(mc, ci);
                    }
                    else {
                        throw new Exception("invalid char found " + stack.peek());
                    }
                }
            }
        }
        System.out.println(mc);
    }
}
