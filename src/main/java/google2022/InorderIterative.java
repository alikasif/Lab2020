package google2022;


import leetcode.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderIterative {

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.left.left = new Node(2);
        root.left.right = new Node(4);
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(8);
        inOrderRecursive(root);
        inOrderIterative(root);
    }

    static void inOrderRecursive(Node root) {
        if(root == null)
            return;

        inOrderRecursive(root.left);
        System.out.print(root+" => ");
        inOrderRecursive(root.right);
    }


    static void inOrderIterative(Node root) {
        int k = 3;
        System.out.println();
        Stack<Node> stack = new Stack<>();

        while (root!= null || !stack.isEmpty()) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if(k==0) {
                System.out.println(root);
                break;
            }
            System.out.println(root);
            root = root.right;
        }
    }
}
