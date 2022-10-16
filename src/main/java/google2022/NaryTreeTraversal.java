package google2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NaryTreeTraversal {

    public static void main(String[] args) {

        MultiNode root = new MultiNode(1);

        root.children.add(new MultiNode(3));
        root.children.add(new MultiNode(2));
        root.children.add(new MultiNode(4));

        root.children.get(0).children.add(new MultiNode(5));
        root.children.get(0).children.add(new MultiNode(6));

        /*root.children.get(0).children.add(new MultiNode(7));

        root.children.get(2).children.add(new MultiNode(8));
        root.children.get(2).children.add(new MultiNode(9));
        root.children.get(2).children.add(new MultiNode(10));
        */
        traverse(root);
        System.out.println("iterative");
        Stack<MultiNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            MultiNode tmp = stack.pop();
            System.out.println(tmp.value);

            for(int i = tmp.children.size()-1; i>=0; i--) {
                stack.push(tmp.children.get(i));
            }
        }
    }

    static void traverse(MultiNode root) {
        if(root == null)
            return;

        System.out.println(root.value);
        for(MultiNode m : root.children)
            traverse(m);
    }
}
