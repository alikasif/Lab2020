package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Tuple {
    String s;
    int count;

    public Tuple(String s, int c){
        this.s = s;
        this.count = c;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "s='" + s + '\'' +
                ", count=" + count +
                '}';
    }
}

public class VerifyPreOrder {

    public static void main(String[] args) {
        String s = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(isValid2("9,#,#,1"));
    }

    static boolean isValid2(String s) {

        String[] split = s.split(",");
        int i = 0;
        List<Tuple> stack = new Stack<>();
        stack.add(new Tuple(split[i], 0));
        i++;

        while( i<split.length ) {

            System.out.println(stack);

            if(i == split.length && !stack.isEmpty()) {
                System.out.println("bad pre order");
                return false;
            }
            if(i == split.length-1 && stack.isEmpty()){
                System.out.println("bad pre order");
                return false;
            }

            String item = split[i];
            Tuple tmp  = stack.get(stack.size()-1);

            if( item.equals("#") ) {
                if(tmp.count == 0)
                    tmp.count++;
                else {
                    stack.remove(tmp);
                }
            }
            else {
                if(tmp.count == 1) {
                    stack.remove(tmp);
                    stack.add(new Tuple(item,0));
                }
                else {
                    tmp.count++;
                    stack.add(new Tuple(item,0));
                }
            }
            i++;
        }
        if(!stack.isEmpty()){
            System.out.println("bad preorder");
            return false;
        }
        return true;
    }

    static boolean isValid(String s) {

        String[] split = s.split(",");
        List<String> stack = new ArrayList<>();

        for(int i = 0; i<split.length; i++) {

            System.out.println(stack);

            if (!split[i].equals("#")) {
                stack.add(split[i]);
                continue;
            }

            if ( stack.size() == 0 && split[i].equals("#") && i == split.length-1 ) {
                System.out.println("correct preorder");
                return true;
            }

            if (split[i].equals("#")) {
                if (!stack.isEmpty()) {
                    String remove = stack.remove(stack.size() - 1);
                }
                else if (stack.size() == 0 && split[i].equals("#") && i == split.length - 1) {
                    System.out.println(" wrong preorder");
                    break;
                }
            }
        }
        return false;
    }
}
