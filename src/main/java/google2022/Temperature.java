package google2022;

import java.util.Arrays;
import java.util.Stack;

public class Temperature {

    public static void main(String[] args) {

        int[] t = {73,74,75,71,69,72,76,73};
        //int[] t = {103,94,85,81,79,77,76,73};

        Stack<Integer> st = new Stack<>();
        int[] res = new int[t.length];

        for(int i=t.length-1; i>=0; i--)
        {
            while(!st.isEmpty() && t[i]>=t[st.peek()])
            {
                st.pop();
            }

            if(!st.isEmpty())
                res[i]=st.peek()-i; // set day

            st.push(i);
            System.out.println(st);
        }
        System.out.println(Arrays.toString(res));
    }
}
