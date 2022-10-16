package leetcode;

import java.util.List;
import java.util.Stack;

public class MinimizeBitString {

    public static void main(String[] args) {

        //String s= "101001";
        String s = "00110";
        List<Integer> stack = new Stack<>();

        for(int i =0; i<s.length(); i++) {
            Integer t = Integer.valueOf(String.valueOf(s.charAt(i)));
            if( !stack.isEmpty() && t == stack.get(stack.size()-1)) {
                stack.remove(stack.size()-1);
            }
            else {
                stack.add(t);
            }
        }
        System.out.println(stack);
    }
}
