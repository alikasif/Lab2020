package google2024.medium;

import java.util.Stack;

public class RemoveAdjacentDupes {

    public static void main(String[] args) {

        String str = "abbaca";
        Stack<Character> stack = new Stack<>();
        Character prev = ' ';
        boolean found = false;
        for(int i=0; i<str.length(); i++) {
            Character curr = str.charAt(i);

            if (stack.isEmpty()) {
                stack.push(curr);
                prev = curr;
                continue;
            }

            if(stack.peek() != curr){
                if (found) {
                    stack.pop();
                    found = false;
                    prev = stack.isEmpty()? ' ':stack.peek();
                    if (curr == prev) {
                        found = true;
                        continue;
                    }
                }
                stack.push(curr);
                prev = curr;
                continue;
            }

            if( curr == prev) {
                found = true;
            }
        }
        System.out.println(stack);
    }
}
