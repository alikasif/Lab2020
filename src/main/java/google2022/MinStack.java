package google2022;

import java.util.Stack;

class StackE {
    int val;
    int minVal;

    public StackE(int v, int m) {
        val =v;
        minVal =m;
    }

}

public class MinStack {

    static Stack<StackE> stack = new Stack<>();

    public static void main(String[] args) {


    }

    static void push(int val) {
        if(stack.isEmpty()) {
            stack.push(new StackE(val, val));
        }
        else {
            StackE peek = stack.peek();
            if(val < peek.minVal) {
                stack.push(new StackE(val, val));
            }
            else{
                stack.push(new StackE(val, peek.minVal));
            }
        }
    }

    static int pop() {
        if(stack.isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        else {
            return stack.pop().val;
        }
    }

    static int getMin() {
        if (stack.isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        else
            return stack.peek().minVal;
    }
}
