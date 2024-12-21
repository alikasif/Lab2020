package google2024.medium;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperature {
    public static void main(String[] args) {

        int[] arr = {73,74,75,71,69,72,76,73};
        Stack<Integer> stack = new Stack<>();
        int ng = 0;

        //https://leetcode.com/problems/daily-temperatures/solutions/1574806/c-easy-standard-sol-intuitive-approach-with-dry-run-stack-appraoch/

        for(int i = arr.length-1; i>=0; i--) {

          while ( !stack.isEmpty() && stack.peek() < arr[i])
                stack.pop();

            ng = stack.isEmpty()? 0:stack.peek();
            stack.push(arr[i]);

            System.out.println(arr[i] + " | "+ ng);
        }

    }

    static public int[] dailyTemperatures2(int[] temperatures) {

        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];

        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }

    static public int[] dailyTemperatures(int[] temperatures) {
        int[] stack = new int[temperatures.length];
        int top = -1;
        int[] ret = new int[temperatures.length];

        for(int i = 0; i < temperatures.length; i++) {
            while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
                int idx = stack[top--];
                ret[idx] = i - idx;
            }
            stack[++top] = i;
        }

        return ret;
    }
}
