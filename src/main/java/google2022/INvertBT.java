package google2022;

import leetcode.Node;

public class INvertBT {
    public static void main(String[] args) {

        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(7);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(9);

        inorder(root);
        invert(root);
        System.out.println("inverted \n");
        inorder(root);
    }

    static void inorder(Node root) {
        if(root == null)
            return;
        inorder(root.left);
        System.out.println(root);
        inorder(root.right);
    }


    static void invert(Node root) {

        if(root == null)
            return;

        Node t = root.left;
        root.left = root.right;
        root.right = t;
        invert(root.left);
        invert(root.right);
    }

}
