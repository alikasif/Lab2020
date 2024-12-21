package google2024.hard;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicCalculator {

    public static void main(String[] args) {
        System.out.println(Arrays.toString("1-2-3".split("\\-")));

        //AtomicInteger atomicInteger = new AtomicInteger(0);
        //int evaluate = evaluate("1+2-4+3-9", atomicInteger);
        //System.out.println(atomicInteger.intValue());
        //int i = evaluateBracket("1+2-4+3-9");
        int i = evaluateBracket("(1+(4+5+2)-3)+(6+8)");
        System.out.println(i);
    }

    public static int evaluateBracket(String s) {
        Stack<String> stack = new Stack<>();
        String multidigit = "";
        for(int i=0; i<s.length(); i++) {

           if(s.charAt(i) == '(') {
               stack.push(String.valueOf(s.charAt(i)));
           }
           else if(s.charAt(i) == ')') {
               String pop = "";
               while (!stack.peek().equals("(")) {
                   pop = stack.pop() + pop;
               }
               stack.pop(); // pop the (;
               AtomicInteger atomicInteger = new AtomicInteger(0);
               evaluate(pop, atomicInteger);
               stack.push(String.valueOf(atomicInteger.intValue()));
           }
           else {
               stack.push(String.valueOf(s.charAt(i)));
           }
        }
        String pop = "";
        while (!stack.isEmpty()) {
            pop = stack.pop() + pop;
        }
        System.out.println("evaluating " +pop);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        evaluate(pop, atomicInteger);
        return atomicInteger.intValue();
    }

    public static int evaluate(String s, AtomicInteger result) {

        System.out.println(" before "+s);

        if(s.contains("+")) {
            System.out.println("++ " + s);
            String[] plusSplit = s.split("\\+");
            for (String s1 : plusSplit) {
                if(s1.contains("-"))
                    evaluate(s1, result);
                else if(!StringUtils.isBlank(s1))
                    result.addAndGet(Integer.valueOf(s1));
            }
        }

        else if(s.contains("-")) {
            System.out.println("-- " + s);
            String[] plusSplit = s.split("\\-");
            result.addAndGet(Integer.valueOf(plusSplit[0]));
            for(int i=1; i<plusSplit.length; i++) {
                result.addAndGet(-1 * Integer.valueOf(plusSplit[i]));
            }
        }
        return result.intValue();
    }

}
