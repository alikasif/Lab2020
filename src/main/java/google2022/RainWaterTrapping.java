package google2022;

import java.util.Stack;

public class RainWaterTrapping {
    public static void main(String[] args) {

        //int [] arr = {3,2,1,0,3};
        int[] arr = {1,0,2,1,0,1,3,2,1,2,1};
        Stack<Integer> stack = new Stack<>();
        int stackopsum = 0;
        int totalwater =0;
        int i=0;

        while(i < arr.length) {

            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int top = stack.pop();
                if(stack.isEmpty())
                    break;

                int d = i - stack.peek() - 1;
                int h = Math.min(arr[i], arr[stack.peek()]) - arr[top];
                totalwater = totalwater + d *h;
                System.out.println(totalwater);
            }
            stack.push(i);
            i++;
        }
        System.out.println(totalwater);
    }
}
