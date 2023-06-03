package salesforce;

import leetcode.Node;

public class MaxWidthBT {

    static int depth =0;
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(3);
        root.left.left = new Node(5);
        root.left.right = new Node(3);
        root.right = new Node(2);
        root.right.right = new Node(9);
        root.right.right.left = new Node(19);

        depth(root.left, 0);
        System.out.println(depth);

        depth=0;
        depth(root.right, 0);
        System.out.println(depth);

    }

    static void depth(Node root, int d) {
        depth = Math.max(depth, d);
        if(root == null) {
            return;
        }

        depth(root.left, d+1);
        depth(root.right, d+1);

    }
}
