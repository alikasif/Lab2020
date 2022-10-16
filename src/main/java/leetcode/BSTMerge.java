package leetcode;

import java.util.List;
import java.util.Stack;

public class BSTMerge {

    public static void main(String[] args) {

        Node root1 = new Node(3);
        root1.left = new Node(1);
        root1.right = new Node(5);

        Node root2 = new Node(4);
        root2.left = new Node(2);
        root2.right = new Node(6);
    }

    static void inOrder(Node root) {

        List<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Node top = stack.get(stack.size()-1);
            if(top.left != null){
                stack.add(top.left);
            }
        }

    }
}
