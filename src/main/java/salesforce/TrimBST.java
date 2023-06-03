package salesforce;

import leetcode.Node;

public class TrimBST {
    public static void main(String[] args) {

        Node root = new Node(3);
        root.left = new Node(0);
        root.right = new Node(4);
        root.left.right = new Node(2);
        root.left.right.left = new Node(1);

    }

    static Node trim(Node root, int low, int high) {

        if(root == null)
            return null;

        if(root.value < low) {
            return trim(root.right, low, high);
        }

        else if(root.value > high) {
            return trim(root.left, low, high);
        }

        root.right = trim(root.right, low, high);
        root.left = trim(root.left, low, high);
        return root;
    }

    static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }
}
