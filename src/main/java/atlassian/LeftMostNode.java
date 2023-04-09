package atlassian;

import leetcode.Node;

public class LeftMostNode {

    static  int maxl = 0;
    public static void main(String[] args) {

        Node root = new Node(3);
/*
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right= new Node(2);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);
*/

        root.right = new Node(1);
        root.right.left = new Node(0);
        root.right.left.left = new Node(1);
        root.right.right = new Node(8);
        root.right.right.right = new Node(8);
        root.right.right.right.right = new Node(8);
        root.right.right.right.right.right = new Node(8);
        //root.right.right.right.right.right.left = new Node(8);
        deepLeft(root, 0, false);
        System.out.println(maxl);
    }

    static void deepLeft(Node root, int l, boolean isleft) {

        if(root == null) {
            return;
        }

        if( isleft) {
            maxl = Math.max(maxl, l);
        }

        deepLeft(root.left, l+1, true);
        deepLeft(root.right, l+1, false);
    }
}
